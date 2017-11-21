package edu.udacity.mou.meeckets.presentation.views.auth;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import edu.udacity.mou.meeckets.presentation.R;
import edu.udacity.mou.meeckets.presentation.R2;
import edu.udacity.mou.meeckets.presentation.views.MeecketsActivity;

/**
 * Created by mou on 11/11/17.
 */

public class AuthActivity extends MeecketsActivity<AuthPresenter, AuthViewModel> {
    public static final String EMPTY = "";

    @Inject
    AuthPresenter presenter;

    @BindView(R2.id.auth_message_text)
    TextView authMessageTextView;

    @BindView(R2.id.auth_username_edit)
    EditText authUsernameEditText;

    @BindView(R2.id.auth_password_edit)
    EditText authPasswordEditText;

    @BindView(R2.id.auth_login_button)
    Button authLoginButton;

    @BindView(R2.id.auth_loading_progress)
    ProgressBar authLoadingProgress;

    @Override
    public int layout() {
        return R.layout.activity_auth;
    }

    @Override
    protected void init() {
        getViewModel().authState().observe(this, this::enableLoading);
        getViewModel().authErrorState().observe(this, this::manageError);
        getViewModel().loginEnabled().observe(this, this::enableLogin);
    }

    @Override
    protected Class<AuthViewModel> viewModel() {
        return AuthViewModel.class;
    }

    @Override
    protected AuthPresenter createPresenter() {
        return presenter;
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    private void showMessage(String token) {
        authMessageTextView.setText(token);
    }

    private void enableLogin(Boolean enabled) {
        authLoginButton.setEnabled(enabled);
    }

    private void enableLoading(AuthViewModel.AuthState authState) {
        switch (authState) {
            case READY:
                onReady();
                break;
            case LOADING:
                onLoading();
                break;
        }
    }

    private void manageError(AuthViewModel.AuthErrorState authErrorState) {
        switch (authErrorState) {
            case NO_ERROR:
                onNoError();
                break;
            case INVALID_CREDENTIALS:
                onInvalidCredentials();
                break;
            case CREATE_ACCOUNT_ERROR:
                onCreateAccountError();
                break;
            case GENERIC_ERROR:
                onGenericError();
                break;
        }
    }

    private void onReady() {
        authLoginButton.setVisibility(View.VISIBLE);
        authLoadingProgress.setVisibility(View.INVISIBLE);
        authUsernameEditText.setEnabled(true);
        authPasswordEditText.setEnabled(true);
    }

    private void onLoading() {
        authLoginButton.setVisibility(View.INVISIBLE);
        authLoadingProgress.setVisibility(View.VISIBLE);
        authUsernameEditText.setEnabled(false);
        authPasswordEditText.setEnabled(false);
    }

    private void onNoError() {
        showMessage(EMPTY);
    }

    private void onInvalidCredentials() {
        showMessage(getString(R.string.user_password_invalid));
        authUsernameEditText.requestFocus();
    }

    private void onCreateAccountError() {
        showMessage(getString(R.string.create_account_error));
    }

    private void onGenericError() {
        showMessage(getString(R.string.generic_server_error));
    }

    @OnTextChanged(R2.id.auth_username_edit)
    protected void onUsernameChange(Editable editable) {
        getPresenter().onUsernameChange(editable.toString());
    }

    @OnTextChanged(R2.id.auth_password_edit)
    protected void onPasswordChange(Editable editable) {
        getPresenter().onPasswordChange(editable.toString());
    }

    @OnClick(R2.id.auth_login_button)
    protected void onLoginClick(View view) {
        getPresenter().onLoginClick();
    }


}
