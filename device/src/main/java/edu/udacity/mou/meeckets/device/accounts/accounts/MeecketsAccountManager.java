package edu.udacity.mou.meeckets.device.accounts.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.udacity.mou.meeckets.domain.device.IMeecketsAccountManager;
import edu.udacity.mou.meeckets.domain.exceptions.accounts.AccountDoesNotExistException;
import edu.udacity.mou.meeckets.domain.exceptions.accounts.CreateAccountException;
import edu.udacity.mou.meeckets.domain.exceptions.accounts.GetAccessTokenException;

/**
 * Created by mou on 11/21/17.
 */

@Singleton
public class MeecketsAccountManager implements IMeecketsAccountManager {
    private Context context;
    private String accountType;
    private String accountName;
    private String tokenName;

    @Inject
    public MeecketsAccountManager(Context context, String accountType, String accountName, String tokenName) {
        this.context = context;
        this.accountType = accountType;
        this.accountName = accountName;
        this.tokenName = tokenName;
    }

    @Override
    public boolean isLogged() {
        try {
            return getAccount() != null;
        } catch (AccountDoesNotExistException e) {
            return false;
        }
    }

    @Override
    public void saveAccount(String token) throws CreateAccountException {
        Account account = new Account(accountName, accountType);
        AccountManager accountManager = AccountManager.get(context);

        if (accountManager.addAccountExplicitly(account, null, null)) {
            accountManager.setAuthToken(account, tokenName, token);
        } else {
            throw new CreateAccountException();
        }
    }

    @Override
    public void deleteAccount() {
        Account account = new Account(accountName, accountType);

        AccountManager.get(context).removeAccount(account, null, null);
    }

    @Override
    public String getAccessToken() throws AccountDoesNotExistException, GetAccessTokenException {
        Account account = getAccount();

        try {
            Bundle bundle = AccountManager.get(context).getAuthToken(account, tokenName, new Bundle(),
                    true, null, null).getResult();

            return bundle.getString(AccountManager.KEY_AUTHTOKEN);
        } catch (Exception e) {
            throw new GetAccessTokenException();
        }
    }

    // This method has MissingPermission suppress warning because we don't need check for another type of accounts
    @SuppressWarnings("MissingPermission")
    private Account[] getAccounts() {
        return AccountManager.get(context).getAccountsByType(accountType);
    }

    private Account getAccount() throws AccountDoesNotExistException {
        try {
            return getAccounts()[0];
        } catch (Exception e) {
            throw new AccountDoesNotExistException();
        }
    }
}
