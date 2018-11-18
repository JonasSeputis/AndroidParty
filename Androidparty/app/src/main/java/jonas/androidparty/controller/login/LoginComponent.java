package jonas.androidparty.controller.login;

import dagger.Component;
import jonas.androidparty.di.components.IApplicationComponent;
import jonas.androidparty.di.scope.PerView;

/**
 * class created by jonasseputis on 18/11/18
 */
@Component(dependencies = IApplicationComponent.class)
@PerView
public interface LoginComponent {
    void inject(LoginController loginController);
}
