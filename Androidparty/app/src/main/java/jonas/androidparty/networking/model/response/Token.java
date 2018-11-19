package jonas.androidparty.networking.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * class created by jonasseputis on 18/11/18
 */
public class Token {

    @SerializedName("token")
    String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
