package jonas.androidparty.networking.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;

import javax.inject.Singleton;

/**
 * class created by jonasseputis on 18/11/18
 */
@Singleton
public class AccountHelper {

    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_TOKEN_TYPE = "token_type";

    private String accountName;
    private String accountType;

    private AccountManager accountManager;

    private Account account;

    public AccountHelper(Context context) {
        this.accountManager = AccountManager.get(context);
        accountName = "Naudotojas";
        accountType = "user";
        init();
    }

    private void init() {
        Account[] accounts = accountManager.getAccountsByType(accountType);
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
    }

    public void createAccount() {
        if (account == null) {
            account = new Account(accountName, accountType);
            accountManager.addAccountExplicitly(account, null, new Bundle());
        }
    }

    public void removeAccount() {
        if (account != null) {
            accountManager.removeAccount(account, null, null);
            account = null;
        }
    }

    public boolean isAccountExists() {
        if (account != null) {
            return true;
        }
        return false;
    }

    public void setAccessToken(String accessToken) {
        if (account != null) {
            accountManager.setUserData(account, KEY_ACCESS_TOKEN, accessToken);
        }
    }

    public String getAccessToken() {
        if (account != null) {
            return accountManager.getUserData(account, KEY_ACCESS_TOKEN);
        }
        return null;
    }

    public void setTokenType(String tokenType) {
        if (account != null) {
            accountManager.setUserData(account, KEY_TOKEN_TYPE, tokenType);
        }
    }

    public String getTokenType() {
        if (account != null) {
            return accountManager.getUserData(account, KEY_TOKEN_TYPE);
        }
        return null;
    }
}
