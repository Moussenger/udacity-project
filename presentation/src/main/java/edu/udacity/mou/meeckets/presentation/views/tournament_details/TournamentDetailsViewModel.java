package edu.udacity.mou.meeckets.presentation.views.tournament_details;

import android.arch.lifecycle.MutableLiveData;

import com.google.android.gms.maps.GoogleMap;

import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.presentation.MeecketsViewModel;

/**
 * Created by mou on 11/21/17.
 */

public class TournamentDetailsViewModel extends MeecketsViewModel<TournamentDetailsPresenter> {
    private MutableLiveData<Tournament> tournament = new MutableLiveData<>();
    private MutableLiveData<Subscription> subscription = new MutableLiveData<>();
    private MutableLiveData<GoogleMap> googleMap = new MutableLiveData<>();

    MutableLiveData<Tournament> tournament() {
        return tournament;
    }

    MutableLiveData<Subscription> subscription() {
        return subscription;
    }

    MutableLiveData<GoogleMap> googleMap() {
        return googleMap;
    }

    TournamentDetailsViewModel tournament(Tournament newTournament) {
        tournament.setValue(newTournament);
        return this;
    }

    TournamentDetailsViewModel subscription(Subscription newSubscription) {
        subscription.setValue(newSubscription);
        return this;
    }

    TournamentDetailsViewModel googleMap(GoogleMap newGoogleMap) {
        googleMap.setValue(newGoogleMap);
        return this;
    }

    @Override
    protected String getTag() {
        return TournamentDetailsViewModel.class.getSimpleName();
    }
}
