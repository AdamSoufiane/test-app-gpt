package application.ports.in;

import application.ports.in.dto.UserRegistrationRequest;
import application.ports.in.dto.UserRegistrationResponse;
import domain.exceptions.UserRegistrationException;

public interface UserRegistrationPort {

    UserRegistrationResponse registerUser(UserRegistrationRequest request) throws UserRegistrationException;

}