package jonas.androidparty.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jonas.androidparty.AndroidApp;
import jonas.androidparty.R;
import jonas.androidparty.controller.login.LoginController;
import jonas.androidparty.di.module.MainActivityModule;
import jonas.androidparty.networking.account.AccountHelper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container)
    ViewGroup container;
    private Router router;
    private MainActivityComponent mainActivityComponent;

    @Inject
    AccountHelper accountHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainActivityComponent = DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule(this)).iApplicationComponent(AndroidApp.getAppComponent(this)).build();
        mainActivityComponent.inject(this);
        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController())
            router.setRoot(RouterTransaction.with(new LoginController()));
    }

    public void setLoginRoot() {
        router.setRoot(RouterTransaction.with(new LoginController()));
    }

    public void setRouterRoot(Controller controller) {
        router.setRoot(RouterTransaction.with(controller));
    }
}
