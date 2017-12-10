package edu.udacity.mou.meeckets.presentation.views.profile;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.arch.lifecycle.LifecycleOwner;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;
import edu.udacity.mou.meeckets.domain.interactors.auth.DoLogout;
import edu.udacity.mou.meeckets.domain.interactors.auth.GetUser;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetSubscriptions;
import edu.udacity.mou.meeckets.domain.model.auth.User;
import edu.udacity.mou.meeckets.presentation.MeecketsPresenter;
import edu.udacity.mou.meeckets.presentation.R;
import edu.udacity.mou.meeckets.presentation.views.auth.AuthActivity;
import timber.log.Timber;

/**
 * Created by mou on 11/11/17.
 */

public class ProfilePresenter extends MeecketsPresenter<ProfileActivity, ProfileViewModel> implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOADER_ID = 2;

    private DoLogout doLogout;
    private GetSubscriptions getSubscriptions;
    private GetUser getUser;
    private LoaderManager loaderManager;

    @Inject
    public ProfilePresenter(DoLogout doLogout, GetSubscriptions getSubscriptions,
                            CheckLogin checkLogin, GetUser getUser) {
        super(checkLogin);
        this.doLogout = doLogout;
        this.getSubscriptions = getSubscriptions;
        this.getUser = getUser;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        if (isViewAttached()) {
            loaderManager = getView().getLoaderManager();

            getUser.run(null).subscribe(this::onUserLoaded);
            getSubscriptions.run(null).subscribe(
                    this::onSubscriptionsUri,
                    this::onSubscriptionsUriError,
                    this::onSubscriptionsUriComplete
            );
        }

    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        checkLogin.run(null).subscribe(this::onCheckLogin);
    }

    public void onLogoutClicked() {
        if (isViewAttached()) {
            new AlertDialog.Builder(getView())
                    .setMessage(R.string.logout_message)
                    .setTitle(R.string.logout_title)
                    .setPositiveButton(R.string.logout, this::onLogout)
                    .setNegativeButton(R.string.cancel, (dialog, id) -> dialog.dismiss())
                    .create()
                    .show();
        }
    }

    private void onLogout(DialogInterface dialog, int id) {
        doLogout.run(null).subscribe(() -> this.onCheckLogin(false));
    }

    private void onCheckLogin(boolean logged) {
        if (isViewAttached() && !logged) {
            AuthActivity.launchClear(getView());
        }
    }

    private void onSubscriptionsUri(String uri) {
        getViewModel().uri(Uri.parse(uri));
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    private void onSubscriptionsUriError(Throwable t) {
        Timber.e(t);
    }

    private void onSubscriptionsUriComplete() {
        Timber.d("URI COMPLETED");
    }

    private void onUserLoaded(User user) {
        getViewModel().user(user);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (isViewAttached()) {
            return new CursorLoader(getView().getApplicationContext(), getViewModel().uri(),
                    null, null, null, null);
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        getViewModel().subscriptionsCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (getViewModel() != null) {
            getViewModel().subscriptionsCursor(null);
        }
    }
}
