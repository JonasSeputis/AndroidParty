package jonas.androidparty.di.components;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;
import jonas.androidparty.AndroidApp;
import jonas.androidparty.controller.login.LoginRepository;
import jonas.androidparty.di.module.ApplicationModule;
import jonas.androidparty.di.module.RepositoryModule;
import jonas.androidparty.networking.AppApi;
import jonas.androidparty.networking.NetworkModule;
import jonas.androidparty.networking.account.AccountHelper;
import jonas.androidparty.networking.sheduler.Main;
import jonas.androidparty.networking.sheduler.SchedulersModule;

/**
 * class created by jonasseputis on 18/11/18
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        RepositoryModule.class,
        NetworkModule.class,
        SchedulersModule.class
})
public interface IApplicationComponent {

    Context getContext();

    Gson getGson();

    AppApi getAppApi();

    AccountHelper getAccountHelper();

    @Main
    Scheduler getMainScheduler();

    void inject(AndroidApp androidApp);

    void inject(LoginRepository loginRepository);
}
