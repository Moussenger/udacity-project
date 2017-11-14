package edu.udacity.mou.meeckets.di.modules.presentation.activities;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.presentation.main.MainActivity;
import edu.udacity.mou.meeckets.presentation.main.MainPresenter;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class MainActivityModule {
    @Provides
    MainPresenter provideMainPresenter(MainActivity activity) {
        return new MainPresenter(activity);
    }
}
