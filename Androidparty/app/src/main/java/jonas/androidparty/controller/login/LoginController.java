package jonas.androidparty.controller.login;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bluelinelabs.conductor.Controller;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jonas.androidparty.AndroidApp;
import jonas.androidparty.R;
import jonas.androidparty.activity.MainActivity;
import jonas.androidparty.controller.list.ListController;

/**
 * class created by jonasseputis on 17/11/18
 */
public class LoginController extends Controller implements LoginView, Button.OnClickListener {

    @BindView(R.id.username_edit_text)
    EditText usernameEditText;
    @BindView(R.id.password_edit_text)
    EditText passwordEditText;
    @BindView(R.id.login_button)
    Button loginButton;

    @Inject
    LoginPresenter presenter;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View root = inflater.inflate(R.layout.controler_login, container, false);
        ButterKnife.bind(this, root);
        DaggerLoginComponent.builder().iApplicationComponent(AndroidApp.getAppComponent(getActivity())).build().inject(this);
        presenter.setView(this);
        loginButton.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                presenter.makeLogin(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                break;
        }
    }

    @Override
    public void successLogin(String token) {
        ((MainActivity) getActivity()).setRouterRoot(new ListController());
    }

    @Override
    public void credentialsError() {
        Toast.makeText(getActivity(), "All fields must be filled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginError() {
        Toast.makeText(getActivity(), "Please, check entered crediantials", Toast.LENGTH_SHORT).show();
    }

}
