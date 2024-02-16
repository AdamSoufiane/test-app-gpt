package domain.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserRegistrationException(String message) {
        super(message);
    }

    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

}