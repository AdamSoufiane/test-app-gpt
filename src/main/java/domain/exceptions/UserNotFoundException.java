package domain.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String userId;

}