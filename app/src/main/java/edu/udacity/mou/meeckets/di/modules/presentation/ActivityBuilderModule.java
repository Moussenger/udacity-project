package edu.udacity.mou.meeckets.di.modules.presentation;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.udacity.mou.meeckets.di.modules.presentation.activities.AuthActivityModule;
import edu.udacity.mou.meeckets.di.modules.presentation.activities.TournamentsActivityModule;
import edu.udacity.mou.meeckets.presentation.views.auth.AuthActivity;
import edu.udacity.mou.meeckets.presentation.views.tournaments.TournamentsActivity;

/**
 * Created by mou on 11/13/17.
 */

@Module
public interface ActivityBuilderModule {
    @ContributesAndroidInjector(modules = AuthActivityModule.class)
    AuthActivity bindAuthActivity();

    @ContributesAndroidInjector(modules = TournamentsActivityModule.class)
    TournamentsActivity bindTournamentsActivity();
}
