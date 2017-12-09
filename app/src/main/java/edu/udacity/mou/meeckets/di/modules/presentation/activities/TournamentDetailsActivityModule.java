package edu.udacity.mou.meeckets.di.modules.presentation.activities;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.AddSubscription;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.DeleteSubscription;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetSubscription;
import edu.udacity.mou.meeckets.presentation.views.tournament_details.TournamentDetailsPresenter;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class TournamentDetailsActivityModule {
    @Provides
    TournamentDetailsPresenter provideTournamentDetailsPresenter(CheckLogin checkLogin, AddSubscription addSubscription,
                                                                 GetSubscription getSubscription, DeleteSubscription deleteSubscription) {
        return new TournamentDetailsPresenter(checkLogin, getSubscription, addSubscription, deleteSubscription);
    }
}
