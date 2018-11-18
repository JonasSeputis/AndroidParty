package jonas.androidparty.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jonas.androidparty.networking.account.AccountHelper;
import jonas.androidparty.utils.RxBus;

/**
 * class created by jonasseputis on 18/11/18
 */
@Module
public class ApplicationModule {

    private final Application app;

    public ApplicationModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    public RxBus providesRxBus() {
        return RxBus.instanceOf();
    }

    @Provides
    @Singleton
    public AccountHelper provideAccountHelper(Context context) {
        return new AccountHelper(context);
    }
}
