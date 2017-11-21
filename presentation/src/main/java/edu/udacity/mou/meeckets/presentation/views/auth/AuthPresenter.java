package edu.udacity.mou.meeckets.presentation.views.auth;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.exceptions.accounts.CreateAccountException;
import edu.udacity.mou.meeckets.domain.exceptions.database.InsertException;
import edu.udacity.mou.meeckets.domain.exceptions.server.InvalidCredentialsException;
import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;
import edu.udacity.mou.meeckets.domain.interactors.auth.DoLogin;
import edu.udacity.mou.meeckets.domain.model.auth.Login;
import edu.udacity.mou.meeckets.presentation.views.MeecketsPresenter;
import edu.udacity.mou.meeckets.presentation.views.tournaments.TournamentsActivity;

/**
 * Created by mou on 11/11/17.
 */

public class AuthPresenter extends MeecketsPresenter<AuthActivity, AuthViewModel> {
    private DoLogin doLogin;
    private CheckLogin checkLogin;

    @Inject
    public AuthPresenter(DoLogin doLogin, CheckLogin checkLogin) {
        this.doLogin = doLogin;
        this.checkLogin = checkLogin;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        checkLogin.run(null).subscribe(this::onCheckLogin);
    }

    public void onUsernameChange(String username) {
        getViewModel().username(username);
        enableLogin();
    }

    public void onPasswordChange(String password) {
        getViewModel().password(password);
        enableLogin();
    }

    public void onLoginClick() {
        getViewModel().loading();
        getViewModel().noError();

        Login parameter = Login.builder()
                .username(getViewModel().username().getValue())
                .password(getViewModel().password().getValue())
                .build();

        doLogin.run(parameter).subscribe(this::onLoginSuccess, this::onLoginError);
    }

    private void launchTournamentsView() {
        AuthActivity view = getView();

        if (view != null) {
            TournamentsActivity.launchClear(view.getApplicationContext());
        }
    }

    private void onCheckLogin(boolean logged) {
        if (logged) {
            launchTournamentsView();
        }
    }

    private void enableLogin() {
        String username = getViewModel().username().getValue();
        String password = getViewModel().password().getValue();
        Boolean loginEnable = !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password);

        getViewModel().loginEnabled(loginEnable);
    }

    private void onLoginSuccess() {
        launchTournamentsView();
    }

    private void onLoginError(Throwable error) {
        getViewModel().ready();

        if (error instanceof InvalidCredentialsException) {
            getViewModel().invalidCredentials();
        } else if (error instanceof CreateAccountException || error instanceof InsertException) {
            getViewModel().createAccountError();
        } else {
            getViewModel().genericError();
        }
    }
}
