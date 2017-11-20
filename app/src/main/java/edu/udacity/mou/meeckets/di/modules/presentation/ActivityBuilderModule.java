package edu.udacity.mou.meeckets.di.modules.presentation;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.udacity.mou.meeckets.di.modules.presentation.activities.MainActivityModule;
import edu.udacity.mou.meeckets.presentation.views.auth.AuthActivity;

/**
 * Created by mou on 11/13/17.
 */

@Module
public interface ActivityBuilderModule {
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    AuthActivity bindMainActivity();
}
