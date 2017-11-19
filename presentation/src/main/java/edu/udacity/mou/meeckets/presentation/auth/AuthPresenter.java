package edu.udacity.mou.meeckets.presentation.auth;

import android.content.Context;
import android.text.TextUtils;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.exceptions.InvalidCredentialsException;
import edu.udacity.mou.meeckets.domain.interactors.auth.DoLogin;
import edu.udacity.mou.meeckets.domain.model.auth.Login;
import edu.udacity.mou.meeckets.presentation.MeecketsPresenter;
import edu.udacity.mou.meeckets.presentation.R;

/**
 * Created by mou on 11/11/17.
 */

public class AuthPresenter extends MeecketsPresenter<AuthActivity, AuthViewModel> {
    private DoLogin doLogin;

    @Inject
    public AuthPresenter(DoLogin doLogin) {
        this.doLogin = doLogin;
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
        clearMessage();

        Login parameter = Login.builder()
                .username(getViewModel().username().getValue())
                .password(getViewModel().password().getValue())
                .build();

        doLogin.run(parameter).subscribe(this::onLoginSuccess, this::onLoginError);
    }

    private void clearMessage() {
        getViewModel().message().setValue("");
    }

    private void enableLogin() {
        String username = getViewModel().username().getValue();
        String password = getViewModel().password().getValue();
        Boolean loginEnable = !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password);

        getViewModel().loginEnabled(loginEnable);
    }

    private void onLoginSuccess() {
        getViewModel().ready();
    }

    private void onLoginError(Throwable error) {
        Context context = getView();

        if (context != null) {
            getViewModel().message(getError(context, error));
        }

        getViewModel().ready();
    }

    private String getError(Context context, Throwable error) {
        if (error instanceof InvalidCredentialsException) {
            return context.getString(R.string.user_password_invalid);
        } else {
            return context.getString(R.string.generic_server_error);
        }
    }
}
