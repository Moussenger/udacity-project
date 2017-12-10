package edu.udacity.mou.meeckets.presentation.views.tournaments;

import android.app.LoaderManager;
import android.arch.lifecycle.LifecycleOwner;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetTournaments;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.presentation.MeecketsPresenter;
import edu.udacity.mou.meeckets.presentation.views.auth.AuthActivity;
import edu.udacity.mou.meeckets.presentation.views.profile.ProfileActivity;
import edu.udacity.mou.meeckets.presentation.views.tournament_details.TournamentDetailsActivity;
import timber.log.Timber;

/**
 * Created by mou on 11/21/17.
 */

public class TournamentsPresenter extends MeecketsPresenter<TournamentsActivity, TournamentsViewModel> implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOADER_ID = 1;

    private GetTournaments getTournaments;
    private LoaderManager loaderManager;

    @Inject
    public TournamentsPresenter(CheckLogin checkLogin, GetTournaments getTournaments) {
        super(checkLogin);
        this.getTournaments = getTournaments;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        if (isViewAttached()) {
            loaderManager = getView().getLoaderManager();

            getTournaments.run(null).subscribe(
                    this::onTournamentsUri,
                    this::onTournamentsUriError,
                    this::onTournamentsUriComplete
            );
        }
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        checkLogin.run(null).subscribe(this::onCheckLogin);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (isViewAttached()) {
            getView().removeListener();
        }

        super.onDestroy(owner);
    }

    public void onTournamentClicked(Tournament tournament) {
        if (isViewAttached()) {
            TournamentDetailsActivity.launch(getView(), tournament);
        }
    }

    public void onProfileClicked() {
        if (isViewAttached()) {
            ProfileActivity.launch(getView());
        }
    }

    private void onCheckLogin(boolean logged) {
        if (isViewAttached() && !logged) {
            AuthActivity.launchClear(getView());
        }
    }

    private void onTournamentsUri(String uri) {
        getViewModel().uri(Uri.parse(uri));
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    private void onTournamentsUriError(Throwable t) {
        Timber.e(t);
    }

    private void onTournamentsUriComplete() {
        Timber.d("URI COMPLETED");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        if (isViewAttached()) {
            return new CursorLoader(getView().getApplicationContext(), getViewModel().uri(),
                    null, null, null, null);
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        getViewModel().tournamentsCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (getViewModel() != null) {
            getViewModel().tournamentsCursor(null);
        }
    }
}
