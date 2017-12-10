package edu.udacity.mou.meeckets.domain.device;

import edu.udacity.mou.meeckets.domain.exceptions.accounts.AccountDoesNotExistException;
import edu.udacity.mou.meeckets.domain.exceptions.accounts.CreateAccountException;
import edu.udacity.mou.meeckets.domain.exceptions.accounts.GetAccessTokenException;

/**
 * Created by mou on 11/21/17.
 */

public interface IMeecketsAccountManager {
    boolean isLogged();

    void saveAccount(String token) throws CreateAccountException;

    void deleteAccount();

    String getAccessToken() throws AccountDoesNotExistException, GetAccessTokenException;
}
