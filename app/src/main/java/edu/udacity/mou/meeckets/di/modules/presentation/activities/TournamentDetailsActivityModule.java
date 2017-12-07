package edu.udacity.mou.meeckets.di.modules.presentation.activities;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;
import edu.udacity.mou.meeckets.presentation.views.tournament_details.TournamentDetailsPresenter;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class TournamentDetailsActivityModule {
    @Provides
    TournamentDetailsPresenter provideTournamentDetailsPresenter(CheckLogin checkLogin) {
        return new TournamentDetailsPresenter(checkLogin);
    }
}
