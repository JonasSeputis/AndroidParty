package jonas.androidparty.networking.model;

import com.google.gson.annotations.SerializedName;

/**
 * class created by jonasseputis on 19/11/18
 */
public class UnauthorizedError {

    @SerializedName("message")
    String messageError;

    public String getMessageError() {
        return messageError;
    }
}
