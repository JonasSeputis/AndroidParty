package jonas.androidparty.response;

import com.google.gson.annotations.SerializedName;

/**
 * class created by jonasseputis on 18/11/18
 */
public class Server {

    @SerializedName("name")
    String serverName;

    @SerializedName("distance")
    Integer distance;

    public String getServerName() {
        return serverName;
    }

    public Integer getDistance() {
        return distance;
    }

}
