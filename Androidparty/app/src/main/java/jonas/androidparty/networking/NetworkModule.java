package jonas.androidparty.networking;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import jonas.androidparty.BuildConfig;
import jonas.androidparty.networking.interceptor.UnauthorizedInterceptor;
import jonas.androidparty.networking.sheduler.Network;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * class created by jonasseputis on 18/11/18
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public Gson privideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }

    @Provides
    @Singleton
    public UnauthorizedInterceptor provideUnauthorizedInterceptor(Gson gson) {
        return new UnauthorizedInterceptor(gson);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(UnauthorizedInterceptor unauthorizedInterceptor) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.d("NetworkModule", message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(unauthorizedInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(
            OkHttpClient okHttpClient,
            GsonConverterFactory gsonConverterFactory,
            @Network Scheduler scheduler
    ) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    public AppApi providePildykApi(Retrofit retrofit) {
        return retrofit.create(AppApi.class);
    }

    @Provides
    @Singleton
    public GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }
}
