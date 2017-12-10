package edu.udacity.mou.meeckets.presentation.views.tournament_details;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.AddSubscription;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.DeleteSubscription;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetSubscription;
import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.presentation.MeecketsPresenter;
import edu.udacity.mou.meeckets.presentation.R;
import edu.udacity.mou.meeckets.presentation.homescreenwidgets.SubscriptionsWidget;
import edu.udacity.mou.meeckets.presentation.views.auth.AuthActivity;
import timber.log.Timber;

import static edu.udacity.mou.meeckets.presentation.views.tournament_details.TournamentDetailsActivity.TOURNAMENT_KEY;

/**
 * Created by mou on 11/21/17.
 */

public class TournamentDetailsPresenter extends MeecketsPresenter<TournamentDetailsActivity, TournamentDetailsViewModel> {
    private GetSubscription getSubscription;
    private AddSubscription addSubscription;
    private DeleteSubscription deleteSubscription;

    @Inject
    public TournamentDetailsPresenter(CheckLogin checkLogin, GetSubscription getSubscription,
                                      AddSubscription addSubscription, DeleteSubscription deleteSubscription) {
        super(checkLogin);
        this.getSubscription = getSubscription;
        this.addSubscription = addSubscription;
        this.deleteSubscription = deleteSubscription;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        if (isViewAttached()) {
            Intent intent = getView().getIntent();

            loadTournament(intent);
        }
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        checkLogin.run(null).subscribe(this::onCheckLogin);
    }

    private void loadTournament(Intent intent) {
        try {
            Tournament tournament = (Tournament) intent.getExtras().getSerializable(TOURNAMENT_KEY);
            getViewModel().tournament(tournament);
            loadSubscription();
        } catch (NullPointerException e) {
            Timber.e(e);
        }
    }

    private void loadSubscription() {
        Tournament tournament = getViewModel().tournament().getValue();

        if (tournament != null) {
            getSubscription.run(tournament.getId()).subscribe(
                    this::onLoadSubscription,
                    this::onLoadSubscriptionError
            );
        }
    }

    private void onCheckLogin(boolean logged) {
        if (isViewAttached() && !logged) {
            AuthActivity.launchClear(getView());
        }
    }

    private void onSubscriptionChanged() {
        if (isViewAttached()) {
            SubscriptionsWidget.updateWidgets(getView().getApplicationContext());
        }

        loadSubscription();
    }

    private void onLoadSubscription(Subscription subscription) {
        getViewModel().subscription(subscription);
    }

    private void onLoadSubscriptionError(Throwable t) {
        if (t instanceof NullPointerException) {
            getViewModel().subscription(null);
        } else {
            Timber.e(t);
            Toast.makeText(getView(), R.string.error_loading_subscription, Toast.LENGTH_SHORT).show();
        }
    }

    private void onAddSubscriptionError(Throwable t) {
        Timber.e(t);

        if (isViewAttached()) {
            Toast.makeText(getView(), R.string.error_adding_subscription, Toast.LENGTH_SHORT).show();
        }
    }

    private void onDeleteSubscriptionError(Throwable t) {
        Timber.e(t);

        if (isViewAttached()) {
            Toast.makeText(getView(), R.string.error_deleting_subscription, Toast.LENGTH_SHORT).show();
        }
    }

    public void onSubscriptionClick() {
        Subscription subscription = getViewModel().subscription().getValue();

        if (subscription == null) {
            Tournament tournament = getViewModel().tournament().getValue();

            addSubscription.run(tournament).subscribe(
                    this::onSubscriptionChanged,
                    this::onAddSubscriptionError
            );
        } else {
            deleteSubscription.run(subscription).subscribe(
                    this::onSubscriptionChanged,
                    this::onDeleteSubscriptionError
            );
        }
    }

    public void onMapLoaded(GoogleMap googleMap) {
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setCompassEnabled(false);

        getViewModel().googleMap(googleMap);
    }
}
