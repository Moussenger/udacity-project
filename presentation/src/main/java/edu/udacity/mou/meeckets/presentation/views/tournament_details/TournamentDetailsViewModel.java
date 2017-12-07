package edu.udacity.mou.meeckets.presentation.views.tournament_details;

import android.arch.lifecycle.MutableLiveData;

import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.presentation.MeecketsViewModel;

/**
 * Created by mou on 11/21/17.
 */

public class TournamentDetailsViewModel extends MeecketsViewModel<TournamentDetailsPresenter> {
    private MutableLiveData<Tournament> tournament = new MutableLiveData<>();

    MutableLiveData<Tournament> tournament() {
        return tournament;
    }

    TournamentDetailsViewModel tournament(Tournament newTournament) {
        tournament.setValue(newTournament);
        return this;
    }

    @Override
    protected String getTag() {
        return TournamentDetailsViewModel.class.getSimpleName();
    }
}
