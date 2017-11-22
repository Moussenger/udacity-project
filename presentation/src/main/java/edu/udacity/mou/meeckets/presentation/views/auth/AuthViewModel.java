package edu.udacity.mou.meeckets.presentation.views.auth;

import android.arch.lifecycle.MutableLiveData;

import edu.udacity.mou.meeckets.presentation.MeecketsViewModel;

/**
 * Created by mou on 11/13/17.
 */

public class AuthViewModel extends MeecketsViewModel<AuthPresenter> {
    public enum AuthState {
        READY, LOADING
    }

    public enum AuthErrorState {
        NO_ERROR, INVALID_CREDENTIALS, CREATE_ACCOUNT_ERROR, GENERIC_ERROR
    }

    private MutableLiveData<AuthState> authState = new MutableLiveData<>();
    private MutableLiveData<AuthErrorState> authErrorState = new MutableLiveData<>();
    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginEnabled = new MutableLiveData<>();

    public AuthViewModel() {
        ready();
    }

    MutableLiveData<AuthState> authState() {
        return authState;
    }

    MutableLiveData<AuthErrorState> authErrorState() {
        return authErrorState;
    }

    MutableLiveData<String> username() {
        return username;
    }

    MutableLiveData<String> password() {
        return password;
    }

    MutableLiveData<Boolean> loginEnabled() {
        return loginEnabled;
    }

    AuthViewModel ready() {
        authState.setValue(AuthState.READY);
        return this;
    }

    AuthViewModel loading() {
        authState.setValue(AuthState.LOADING);
        return this;
    }

    AuthViewModel noError() {
        authErrorState.setValue(AuthErrorState.NO_ERROR);
        return this;
    }

    AuthViewModel invalidCredentials() {
        authErrorState.setValue(AuthErrorState.INVALID_CREDENTIALS);
        return this;
    }

    AuthViewModel createAccountError() {
        authErrorState.setValue(AuthErrorState.CREATE_ACCOUNT_ERROR);
        return this;
    }

    AuthViewModel genericError() {
        authErrorState.setValue(AuthErrorState.GENERIC_ERROR);
        return this;
    }

    AuthViewModel username(String newUsername) {
        username.setValue(newUsername);
        return this;
    }

    AuthViewModel password(String newPassword) {
        password.setValue(newPassword);
        return this;
    }

    AuthViewModel loginEnabled(Boolean newLoginEnabled) {
        loginEnabled.setValue(newLoginEnabled);
        return this;
    }


    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }
}
