package application.ports.in;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserRegistrationRequest {

    @NotNull(message = "Name cannot be null.")
    private String name;

    @NotNull(message = "Email cannot be null.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotNull(message = "Password cannot be null.")
    private String password;

    public UserRegistrationRequest() {
    }

    public UserRegistrationRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
