package edu.udacity.mou.meeckets.di.modules.presentation.activities;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;
import edu.udacity.mou.meeckets.domain.interactors.auth.DoLogin;
import edu.udacity.mou.meeckets.presentation.views.auth.AuthPresenter;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class AuthActivityModule {
    @Provides
    AuthPresenter provideAuthPresenter(DoLogin doLogin, CheckLogin checkLogin) {
        return new AuthPresenter(doLogin, checkLogin);
    }
}
