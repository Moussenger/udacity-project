package edu.udacity.mou.meeckets.data.datasources.database;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.domain.exceptions.database.InsertException;
import edu.udacity.mou.meeckets.domain.model.auth.User;

/**
 * Created by mou on 11/14/17.
 */

@Singleton
public class MeecketsDatabaseDatasource implements IMeecketsDatabaseDatasource {
    private Context context;
    private StorageMapper<User> userStorageMapper;

    @Inject
    public MeecketsDatabaseDatasource(Context context, StorageMapper<User> userStorageMapper) {
        this.context = context;
        this.userStorageMapper = userStorageMapper;
    }

    @Override
    public void addUser(User user) throws InsertException {
        ContentValues contentValues = userStorageMapper.values(user);
        Uri uri = context.getContentResolver().insert(MeecketsProvider.Users.CONTENT_URI, contentValues);

        if (uri == null) {
            throw new InsertException();
        }
    }

    @Override
    public void deleteUser() {
        context.getContentResolver().delete(MeecketsProvider.Users.CONTENT_URI, null, null);
    }
}
