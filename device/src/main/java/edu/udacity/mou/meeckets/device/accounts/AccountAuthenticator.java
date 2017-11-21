package edu.udacity.mou.meeckets.device.accounts;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * Created by mou on 11/21/17.
 */

public class AccountAuthenticator extends AbstractAccountAuthenticator {
    private Context context;

    public AccountAuthenticator(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String s) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String s, String s1, String[] strings, Bundle bundle) throws NetworkErrorException {
        return new Bundle();
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String tokenType, Bundle bundle) throws NetworkErrorException {
        final AccountManager accountManager = AccountManager.get(context);

        String authToken = accountManager.peekAuthToken(account, tokenType);

        if (!TextUtils.isEmpty(authToken)) {
            return new Bundle();
        } else {
            final Bundle result = new Bundle();
            result.putString(android.accounts.AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(android.accounts.AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(android.accounts.AccountManager.KEY_AUTHTOKEN, authToken);
            return result;
        }
    }

    @Override
    public String getAuthTokenLabel(String s) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String s, Bundle bundle) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strings) throws NetworkErrorException {
        return null;
    }
}
