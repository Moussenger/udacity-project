package edu.udacity.mou.meeckets.data.datasources.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.domain.exceptions.database.InsertException;
import edu.udacity.mou.meeckets.domain.model.auth.User;
import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;

/**
 * Created by mou on 11/14/17.
 */

@Singleton
public class MeecketsDatabaseDatasource implements IMeecketsDatabaseDatasource {
    private Context context;
    private StorageMapper<User> userStorageMapper;
    private StorageMapper<Tournament> tournamentStorageMapper;
    private StorageMapper<Subscription> subscriptionStorageMapper;

    @Inject
    public MeecketsDatabaseDatasource(Context context, StorageMapper<User> userStorageMapper,
                                      StorageMapper<Tournament> tournamentStorageMapper,
                                      StorageMapper<Subscription> subscriptionStorageMapper) {
        this.context = context;
        this.userStorageMapper = userStorageMapper;
        this.tournamentStorageMapper = tournamentStorageMapper;
        this.subscriptionStorageMapper = subscriptionStorageMapper;
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

    @Override
    public Uri getTournaments() {
        return MeecketsProvider.Tournaments.CONTENT_URI;
    }

    @Override
    public void saveTournaments(List<Tournament> tournaments) {
        ContentValues[] contentValues = tournamentStorageMapper.values(tournaments);

        context.getContentResolver().bulkInsert(MeecketsProvider.Tournaments.CONTENT_URI, contentValues);
    }

    @Override
    public void addSubscription(Subscription subscription) {
        ContentValues contentValues = subscriptionStorageMapper.values(subscription);

        context.getContentResolver().insert(MeecketsProvider.Subscriptions.CONTENT_URI, contentValues);
    }

    @Override
    public Uri getSubscriptions() {
        return MeecketsProvider.Subscriptions.CONTENT_URI;
    }

    @Override
    public Subscription getSubscription(long id) {
        Cursor cursor = context.getContentResolver().query(MeecketsProvider.Subscriptions.CONTENT_URI, null, "id=?", new String[]{String.valueOf(id)}, null);
        Subscription subscription = subscriptionStorageMapper.single(cursor);
        cursor.close();

        return subscription;
    }

    @Override
    public void deleteSubscription(long id) {
        context.getContentResolver().delete(MeecketsProvider.Subscriptions.CONTENT_URI, "id=?", new String[]{String.valueOf(id)});
    }

    @Override
    public void deleteSubscriptions() {
        context.getContentResolver().delete(MeecketsProvider.Subscriptions.CONTENT_URI, null, null);
    }
}
