package infrastructure.adapters.out.persistence;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import application.ports.out.UserRepositoryPort;
import domain.entities.UserEntity;
import java.util.Optional;
import org.springframework.dao.DataAccessException;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryAdapter.class);
    private final JpaUserRepository jpaUserRepository;

    @Override
    @Transactional
    public UserEntity saveUser(UserEntity userEntity) {
        try {
            // Assume validation is handled outside via annotations or a validation component
            return jpaUserRepository.save(userEntity);
        } catch (DataAccessException e) {
            log.error("Error saving user: ", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findUserByEmail(String email) {
        try {
            return jpaUserRepository.findByEmail(email);
        } catch (DataAccessException e) {
            log.error("Error finding user by email: ", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        try {
            jpaUserRepository.deleteById(id);
        } catch (DataAccessException e) {
            log.error("Error deleting user by ID: ", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public UserEntity updateUser(UserEntity userEntity) {
        try {
            // Assume validation is handled outside via annotations or a validation component
            return jpaUserRepository.save(userEntity);
        } catch (DataAccessException e) {
            log.error("Error updating user: ", e);
            throw e;
        }
    }

    interface JpaUserRepository extends org.springframework.data.jpa.repository.JpaRepository<UserEntity, Long> {
        Optional<UserEntity> findByEmail(String email);
        void deleteById(Long id);
    }
}