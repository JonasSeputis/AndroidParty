package jonas.androidparty.di.module;

import dagger.Module;
import jonas.androidparty.activity.MainActivity;

/**
 * class created by jonasseputis on 18/11/18
 */
@Module
public class MainActivityModule {

    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
