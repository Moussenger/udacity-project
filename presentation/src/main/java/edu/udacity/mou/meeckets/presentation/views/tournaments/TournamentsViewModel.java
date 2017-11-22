package edu.udacity.mou.meeckets.presentation.views.tournaments;

import android.arch.lifecycle.MutableLiveData;
import android.database.Cursor;
import android.net.Uri;

import edu.udacity.mou.meeckets.presentation.MeecketsViewModel;

/**
 * Created by mou on 11/21/17.
 */

public class TournamentsViewModel extends MeecketsViewModel<TournamentsPresenter> {
    private Uri uri;
    private MutableLiveData<Cursor> tournamentsCursor = new MutableLiveData<>();

    Uri uri() {
        return uri;
    }

    MutableLiveData<Cursor> tournamentsCursor() {
        return tournamentsCursor;
    }

    TournamentsViewModel uri(Uri uri) {
        this.uri = uri;
        return this;
    }

    TournamentsViewModel tournamentsCursor(Cursor tournamentsCursor) {
        this.tournamentsCursor.setValue(tournamentsCursor);
        return this;
    }

    @Override
    protected String getTag() {
        return TournamentsViewModel.class.getSimpleName();
    }
}
