package application.ports.out;

import domain.entities.UserEntity;
import java.util.Optional;

/**
 * This interface defines the contract for user persistence mechanisms.
 * It declares methods for saving and retrieving user entities,
 * which will be implemented by an adapter in the infrastructure layer.
 */
public interface UserRepositoryPort {

    /**
     * Persists a given UserEntity into the database.
     * @param userEntity the user entity to save
     * @return the persisted UserEntity with an assigned ID
     */
    UserEntity saveUser(UserEntity userEntity);

    /**
     * Retrieves a UserEntity by its email if it exists.
     * @param email the email to search for
     * @return an Optional containing the found UserEntity or empty if not found
     */
    Optional<UserEntity> findUserByEmail(String email);

    /**
     * Deletes a UserEntity from the database by its unique identifier.
     * @param id the ID of the user to delete
     * @return true if the user was deleted successfully, false if the user was not found
     */
    boolean deleteUserById(Long id);

    /**
     * Updates an existing UserEntity with new information in the database.
     * @param userEntity the user entity to update
     * @return the updated UserEntity
     */
    UserEntity updateUser(UserEntity userEntity);
}