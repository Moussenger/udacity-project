package edu.udacity.mou.meeckets.di.modules.presentation;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.udacity.mou.meeckets.di.modules.presentation.activities.AuthActivityModule;
import edu.udacity.mou.meeckets.di.modules.presentation.activities.ProfileActivityModule;
import edu.udacity.mou.meeckets.di.modules.presentation.activities.TournamentDetailsActivityModule;
import edu.udacity.mou.meeckets.di.modules.presentation.activities.TournamentsActivityModule;
import edu.udacity.mou.meeckets.presentation.views.auth.AuthActivity;
import edu.udacity.mou.meeckets.presentation.views.profile.ProfileActivity;
import edu.udacity.mou.meeckets.presentation.views.tournament_details.TournamentDetailsActivity;
import edu.udacity.mou.meeckets.presentation.views.tournaments.TournamentsActivity;

/**
 * Created by mou on 11/13/17.
 */

@Module
public interface ActivityBuilderModule {
    @ContributesAndroidInjector(modules = AuthActivityModule.class)
    AuthActivity bindAuthActivity();

    @ContributesAndroidInjector(modules = ProfileActivityModule.class)
    ProfileActivity bindProfileActivity();

    @ContributesAndroidInjector(modules = TournamentsActivityModule.class)
    TournamentsActivity bindTournamentsActivity();

    @ContributesAndroidInjector(modules = TournamentDetailsActivityModule.class)
    TournamentDetailsActivity bindTournamentDetailsActivity();
}
