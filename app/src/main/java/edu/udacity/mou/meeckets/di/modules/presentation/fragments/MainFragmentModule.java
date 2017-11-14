package edu.udacity.mou.meeckets.di.modules.presentation.fragments;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.domain.interactors.auth.DoLogin;
import edu.udacity.mou.meeckets.presentation.main.MainFragment;
import edu.udacity.mou.meeckets.presentation.main.MainFragmentPresenter;

/**
 * Created by mou on 11/14/17.
 */

@Module
public class MainFragmentModule {
    @Provides
    MainFragmentPresenter provideMainPresenter(MainFragment fragment, DoLogin doLoginUseCase) {
        return new MainFragmentPresenter(fragment, doLoginUseCase);
    }
}
