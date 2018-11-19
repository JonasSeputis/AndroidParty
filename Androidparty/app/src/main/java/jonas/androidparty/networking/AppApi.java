package jonas.androidparty.networking;

import java.util.List;

import io.reactivex.Observable;
import jonas.androidparty.networking.model.response.Server;
import jonas.androidparty.networking.model.response.Token;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * class created by jonasseputis on 18/11/18
 */
public interface AppApi {

    @POST("/v1/tokens")
    @FormUrlEncoded
    Observable<Token> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("/v1/servers")
    Observable<List<Server>> servers(@Header("Authorization") String token);
}
