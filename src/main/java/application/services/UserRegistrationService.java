package application.services;

import application.ports.in.UserRegistrationPort;
import application.ports.in.UserRegistrationRequest;
import application.ports.in.UserRegistrationResponse;
import application.ports.out.UserRepositoryPort;
import domain.entities.UserEntity;
import domain.exceptions.UserRegistrationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegistrationService implements UserRegistrationPort {

    private final UserRepositoryPort userRepositoryPort;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserInputValidator userInputValidator;

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest request) throws UserRegistrationException {
        if (!userInputValidator.validate(request)) {
            return new UserRegistrationResponse(null, null, request.getEmail(), "FAILURE", "Invalid user registration data.");
        }

        Optional<UserEntity> existingUser = userRepositoryPort.findUserByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            return new UserRegistrationResponse(null, null, request.getEmail(), "FAILURE", "Email already in use.");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        UserEntity savedUser = userRepositoryPort.saveUser(userEntity);

        return new UserRegistrationResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), "SUCCESS", null);
    }
}
