package infrastructure.adapters.in.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import application.ports.in.UserRegistrationPort;
import application.ports.in.UserRegistrationRequest;
import application.ports.in.UserRegistrationResponse;
import domain.exceptions.UserRegistrationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/users")
public class UserRegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

    private final UserRegistrationPort userRegistrationPort;

    public UserRegistrationController(UserRegistrationPort userRegistrationPort) {
        this.userRegistrationPort = userRegistrationPort;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody UserRegistrationRequest request) {
        try {
            UserRegistrationResponse response = userRegistrationPort.registerUser(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UserRegistrationException e) {
            logger.error("Registration failed for user: {} with error: {}", request, e.getMessage());
            // Handle different types of UserRegistrationException
            HttpStatus status;
            if (e instanceof SpecificUserRegistrationException) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                status = HttpStatus.UNPROCESSABLE_ENTITY;
            }
            // Assuming UserRegistrationResponse has a constructor to handle exceptions
            UserRegistrationResponse errorResponse = new UserRegistrationResponse(e.getUserId(), e.getMessage());
            return ResponseEntity.status(status).body(errorResponse);
        }
    }

}