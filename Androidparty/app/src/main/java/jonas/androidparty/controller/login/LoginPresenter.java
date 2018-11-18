package jonas.androidparty.controller.login;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import jonas.androidparty.BuildConfig;
import jonas.androidparty.base.BasePresenter;
import jonas.androidparty.networking.account.AccountHelper;
import jonas.androidparty.networking.sheduler.Main;
import jonas.androidparty.response.Token;
import jonas.androidparty.utils.TextUtils;

/**
 * class created by jonasseputis on 18/11/18
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginRepository loginRepository;
    private AccountHelper accountHelper;
    private Scheduler scheduler;

    @Inject
    public LoginPresenter(LoginRepository loginRepository, AccountHelper accountHelper, @Main Scheduler scheduler) {
        this.loginRepository = loginRepository;
        this.accountHelper = accountHelper;
        this.scheduler = scheduler;
    }

    public void makeLogin(String username, String password) {
        if (checkUsernameAndPassword(username, password))
            loginObservable(username, password);
    }

    private boolean checkUsernameAndPassword(String username, String password) {
        if (!TextUtils.isTextIsEmptyOrNull(username) && !TextUtils.isTextIsEmptyOrNull(password)) {
            if(username.equals(BuildConfig.USERNAME) && password.equals(BuildConfig.PASSWORD)) {
                return true;
            } else {
                if (hasView())
                    getView().loginError();
                return false;
            }
        } else {
            if (hasView())
                getView().credentialsError();
            return false;
        }
    }

    private void loginObservable(String username, String password) {
        retrieveLoginObservable(username, password).subscribe();
    }

    private Observable<Token> retrieveLoginObservable(String username, String password) {
        return loginRepository.login(username, password)
                .observeOn(scheduler)
                .doOnSubscribe(subscriptions::add)
                .doOnNext(token -> {
                    accountHelper.createAccount();
                    accountHelper.setTokenType("Bearer");
                    accountHelper.setAccessToken(token.getAccessToken());
                    if (hasView())
                        getView().successLogin(token.getAccessToken());
                })
                .doOnError(e -> {
                    if (hasView())
                        getView().loginError();
                });

    }

}
