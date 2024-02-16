package application.ports.in;

import lombok.Value;

@Value
public class UserRegistrationResponse {
    Long userId;
    String username;
    String email;
    RegistrationStatus registrationStatus;
    String errorMessage;
}
