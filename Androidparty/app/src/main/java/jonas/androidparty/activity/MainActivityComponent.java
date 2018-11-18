package jonas.androidparty.activity;

import dagger.Component;
import jonas.androidparty.di.components.IApplicationComponent;
import jonas.androidparty.di.module.MainActivityModule;
import jonas.androidparty.di.scope.MainActivityScope;

/**
 * class created by jonasseputis on 18/11/18
 */
@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = IApplicationComponent.class)
public interface MainActivityComponent extends IApplicationComponent {
    MainActivity inject(MainActivity mainActivity);
}
