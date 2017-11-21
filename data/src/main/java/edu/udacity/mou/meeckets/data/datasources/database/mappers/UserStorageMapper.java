package edu.udacity.mou.meeckets.data.datasources.database.mappers;

import android.content.ContentValues;
import android.database.Cursor;

import edu.udacity.mou.meeckets.domain.model.auth.User;

import static edu.udacity.mou.meeckets.data.datasources.database.columns.UserColumns.PROFILE_IMAGE;
import static edu.udacity.mou.meeckets.data.datasources.database.columns.UserColumns.USERNAME;

/**
 * Created by mou on 11/21/17.
 */

public class UserStorageMapper extends StorageMapper<User> {

    @Override
    protected ContentValues fromValues(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, user.getUsername());
        contentValues.put(PROFILE_IMAGE, user.getProfileImage());

        return contentValues;
    }

    @Override
    protected User fromCursor(Cursor cursor) {
        return User.builder()
                .username(getString(cursor, USERNAME))
                .profileImage(getString(cursor, PROFILE_IMAGE))
                .build();
    }
}
