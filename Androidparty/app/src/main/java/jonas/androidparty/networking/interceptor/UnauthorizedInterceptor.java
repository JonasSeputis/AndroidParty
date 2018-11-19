package jonas.androidparty.networking.interceptor;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.nio.charset.Charset;

import jonas.androidparty.networking.exeption.ApiException;
import jonas.androidparty.networking.model.UnauthorizedError;
import okhttp3.Interceptor;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 * class created by jonasseputis on 19/11/18
 */
public class UnauthorizedInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private final Gson gson;

    public UnauthorizedInterceptor(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        handleResponse(response);
        return response;
    }

    private void handleResponse(Response response) throws IOException {
        if (response.code() == 401) {
            Buffer buffer = getBuffer(response);
            try {
                UnauthorizedError body = gson.fromJson(readBuffer(buffer), UnauthorizedError.class);
                throw new ApiException(body);
            } catch (JsonParseException cause) {
                throw new RuntimeException("failed to parse network error", cause);
            }
        }
    }

    private Buffer getBuffer(Response response) throws IOException {
        BufferedSource source = response.body().source();
        source.request(java.lang.Long.MAX_VALUE);
        return source.buffer();
    }

    private String readBuffer(Buffer buffer) {
        return buffer.clone().readString(UTF8);
    }
}
