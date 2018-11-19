package jonas.androidparty.networking.exeption;

import jonas.androidparty.networking.model.UnauthorizedError;

/**
 * class created by jonasseputis on 19/11/18
 */
public class ApiException extends RuntimeException {

    public static final String INVALID_CREDENTIALS = "Unauthorized";

    public final UnauthorizedError response;

    public ApiException(UnauthorizedError response) {
        this.response = response;
    }
}
