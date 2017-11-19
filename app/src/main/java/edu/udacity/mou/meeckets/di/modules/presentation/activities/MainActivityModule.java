package edu.udacity.mou.meeckets.di.modules.presentation.activities;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.domain.interactors.auth.DoLogin;
import edu.udacity.mou.meeckets.presentation.auth.AuthPresenter;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class MainActivityModule {
    @Provides
    AuthPresenter provideMainPresenter(DoLogin doLogin) {
        return new AuthPresenter(doLogin);
    }
}
