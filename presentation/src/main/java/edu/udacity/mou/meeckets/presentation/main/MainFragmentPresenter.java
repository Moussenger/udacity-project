package edu.udacity.mou.meeckets.presentation.main;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.auth.DoLogin;
import edu.udacity.mou.meeckets.domain.model.auth.Login;
import edu.udacity.mou.meeckets.presentation.MeecketsPresenter;

/**
 * Created by mou on 11/11/17.
 */

public class MainFragmentPresenter extends MeecketsPresenter<MainFragment, MainViewModel> {

    private DoLogin doLogin;

    @Inject
    public MainFragmentPresenter(MainFragment mainFragment, DoLogin doLogin) {
        super(mainFragment);
        this.doLogin = doLogin;
    }

    public void onLoginClick() {
        getViewModel().token("loading...");

        Login parameter = Login.builder()
                .username("patata")
                .password("patata")
                .build();

        disposable(doLogin.run(parameter).subscribe(this::onLoginSuccess, this::onLoginError));
    }

    private void onLoginSuccess() {
        getViewModel().token("Token success");
    }

    private void onLoginError(Throwable error) {
        getViewModel().token("LOGIN ERROR - " + error.getMessage());
    }
}
