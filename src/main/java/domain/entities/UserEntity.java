package domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class UserEntity {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    public void setPassword(String password) {
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("The password must not be null and should meet complexity requirements including digits, lower and upper case letters, and special characters.");
        }
        // The password encoding should be handled by an external utility or service.
        // This is a placeholder for such functionality.
        this.password = password;
    }

    private boolean isValidPassword(String password) {
        return password != null && password.matches("(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}");
    }

}