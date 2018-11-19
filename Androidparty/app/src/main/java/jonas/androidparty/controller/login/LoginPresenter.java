package jonas.androidparty.controller.login;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import jonas.androidparty.base.BasePresenter;
import jonas.androidparty.networking.account.AccountHelper;
import jonas.androidparty.networking.exeption.ApiException;
import jonas.androidparty.networking.model.response.Token;
import jonas.androidparty.networking.sheduler.Main;
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
            handleLoginObservable(username, password);
    }

    private boolean checkUsernameAndPassword(String username, String password) {
        if (TextUtils.isTextIsEmptyOrNull(username) || TextUtils.isTextIsEmptyOrNull(password)) {
            getView().credentialsError();
            return false;
        }
        return true;
    }

    private void handleLoginObservable(String username, String password) {
        loginRepository.login(username, password)
                .observeOn(scheduler)
                .subscribe(new Observer<Token>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        subscriptions.add(d);
                    }

                    @Override
                    public void onNext(Token token) {
                        accountHelper.createAccount();
                        accountHelper.setTokenType("Bearer");
                        accountHelper.setAccessToken(token.getAccessToken());
                        getView().successLogin(token.getAccessToken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof ApiException) {
                            switch (((ApiException) e).response.getMessageError()) {
                                case ApiException.INVALID_CREDENTIALS:
                                    getView().loginError();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
