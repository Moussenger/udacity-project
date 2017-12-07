package edu.udacity.mou.meeckets.presentation.views.tournament_details;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.presentation.MeecketsPresenter;
import edu.udacity.mou.meeckets.presentation.views.auth.AuthActivity;
import timber.log.Timber;

import static edu.udacity.mou.meeckets.presentation.views.tournament_details.TournamentDetailsActivity.TOURNAMENT_KEY;

/**
 * Created by mou on 11/21/17.
 */

public class TournamentDetailsPresenter extends MeecketsPresenter<TournamentDetailsActivity, TournamentDetailsViewModel> {
    @Inject
    public TournamentDetailsPresenter(CheckLogin checkLogin) {
        super(checkLogin);
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        if (isViewAttached()) {
            Intent intent = getView().getIntent();

            try {
                Tournament tournament = (Tournament) intent.getExtras().getSerializable(TOURNAMENT_KEY);
                getViewModel().tournament(tournament);
            } catch (NullPointerException e) {
                Timber.e(e);
            }
        }
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        checkLogin.run(null).subscribe(this::onCheckLogin);
    }

    private void onCheckLogin(boolean logged) {
        if (isViewAttached() && !logged) {
            AuthActivity.launchClear(getView());
        }
    }

    public void onMapLoaded(GoogleMap googleMap) {
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setCompassEnabled(false);

        getViewModel().googleMap(googleMap);
    }
}
