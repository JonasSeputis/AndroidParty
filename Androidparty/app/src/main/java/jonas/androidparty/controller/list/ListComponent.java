package jonas.androidparty.controller.list;

import dagger.Component;
import jonas.androidparty.di.components.IApplicationComponent;
import jonas.androidparty.di.scope.PerView;

/**
 * class created by jonasseputis on 18/11/18
 */
@Component(dependencies = IApplicationComponent.class)
@PerView
public interface ListComponent {
    void inject(ListController listController);
}
