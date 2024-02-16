package infrastructure.exceptions;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPersistenceException extends Exception {

    private String errorCode;
    private LocalDateTime errorTimestamp;

    public UserPersistenceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorTimestamp = LocalDateTime.now();
    }

    public UserPersistenceException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorTimestamp = LocalDateTime.now();
    }
}