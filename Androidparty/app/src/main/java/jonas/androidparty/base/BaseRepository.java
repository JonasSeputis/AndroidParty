package jonas.androidparty.base;

import jonas.androidparty.networking.AppApi;
import jonas.androidparty.networking.account.AccountHelper;

/**
 * class created by jonasseputis on 18/11/18
 */
public class BaseRepository {

    protected AppApi appApi;
    protected AccountHelper accountHelper;

    public BaseRepository(AppApi appApi, AccountHelper accountHelper) {
        this.appApi = appApi;
        this.accountHelper = accountHelper;
    }

    protected String token() {
        return String.format(accountHelper.getTokenType() + " " + accountHelper.getAccessToken());
    }
}
