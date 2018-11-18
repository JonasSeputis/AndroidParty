package jonas.androidparty.controller.login;

/**
 * class created by jonasseputis on 18/11/18
 */
public interface LoginView {

    void successLogin(String token);

    void credentialsError();

    void loginError();
}
