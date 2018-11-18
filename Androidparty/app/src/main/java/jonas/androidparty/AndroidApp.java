package jonas.androidparty;

import android.app.Application;
import android.content.Context;

import jonas.androidparty.di.components.DaggerIApplicationComponent;
import jonas.androidparty.di.components.IApplicationComponent;
import jonas.androidparty.di.module.ApplicationModule;

/**
 * class created by jonasseputis on 18/11/18
 */
public class AndroidApp extends Application {

    private IApplicationComponent applicationComponent;

    public static IApplicationComponent getAppComponent(Context context) {
        AndroidApp app = (AndroidApp) context.getApplicationContext();
        if (app.applicationComponent == null) {
            app.applicationComponent = DaggerIApplicationComponent.builder()
                    .applicationModule(app.getApplicationModule())
                    .build();
        }
        return app.applicationComponent;
    }

    public static Application get(Context context) {
        return (AndroidApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent(this).inject(this);
    }

    protected ApplicationModule getApplicationModule() {
        return new ApplicationModule(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
