package edu.udacity.mou.meeckets.presentation;

import android.arch.lifecycle.ViewModel;

import timber.log.Timber;

/**
 * Created by mou on 11/13/17.
 */

public abstract class MeecketsViewModel<P extends MeecketsPresenter> extends ViewModel {
    private P presenter;

    P presenter() {
        return presenter;
    }

    void presenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onCleared() {
        if (presenter != null) {
            presenter.onViewModelCleared();
            presenter = null;

            Timber.d(getTag() + " - Presenter cleared");
        }
    }

    protected abstract String getTag();
}
