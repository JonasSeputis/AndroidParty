package jonas.androidparty.controller.login;

import javax.inject.Inject;

import io.reactivex.Observable;
import jonas.androidparty.base.BaseRepository;
import jonas.androidparty.networking.AppApi;
import jonas.androidparty.networking.account.AccountHelper;
import jonas.androidparty.networking.model.response.Token;

/**
 * class created by jonasseputis on 18/11/18
 */
public class LoginRepository extends BaseRepository {

    @Inject
    public LoginRepository(AppApi appApi, AccountHelper accountHelper) {
        super(appApi, accountHelper);
    }

    public Observable<Token> login(String username, String password) {
        return appApi.login(username, password);
    }

}
