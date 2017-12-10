package edu.udacity.mou.meeckets.presentation.views.profile;

import android.arch.lifecycle.MutableLiveData;
import android.database.Cursor;
import android.net.Uri;

import edu.udacity.mou.meeckets.domain.model.auth.User;
import edu.udacity.mou.meeckets.presentation.MeecketsViewModel;

/**
 * Created by mou on 11/13/17.
 */

public class ProfileViewModel extends MeecketsViewModel<ProfilePresenter> {
    private MutableLiveData<User> user = new MutableLiveData<>();
    private MutableLiveData<Cursor> subscriptionsCursor = new MutableLiveData<>();
    private Uri uri;

    MutableLiveData<User> user() {
        return user;
    }

    MutableLiveData<Cursor> subscriptionsCursor() {
        return subscriptionsCursor;
    }

    Uri uri() {
        return uri;
    }

    ProfileViewModel uri(Uri uri) {
        this.uri = uri;
        return this;
    }

    ProfileViewModel user(User newUser) {
        user.setValue(newUser);
        return this;
    }

    ProfileViewModel subscriptionsCursor(Cursor newSubscriptionsCursor) {
        this.subscriptionsCursor.setValue(newSubscriptionsCursor);
        return this;
    }


    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }
}
