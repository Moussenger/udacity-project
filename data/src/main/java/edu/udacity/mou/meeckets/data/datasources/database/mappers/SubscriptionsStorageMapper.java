package edu.udacity.mou.meeckets.data.datasources.database.mappers;

import android.content.ContentValues;
import android.database.Cursor;

import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;

import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentSubscriptionColumns.DATE;
import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentSubscriptionColumns.ID;
import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentSubscriptionColumns.IMAGE;
import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentSubscriptionColumns.NAME;
import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentSubscriptionColumns.PLACE;

/**
 * Created by mou on 11/21/17.
 */

public class SubscriptionsStorageMapper extends StorageMapper<Subscription> {

    @Override
    protected ContentValues fromValues(Subscription subscription) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, subscription.getId());
        contentValues.put(NAME, subscription.getName());
        contentValues.put(IMAGE, subscription.getImage());
        contentValues.put(DATE, convertDate(subscription.getDate()));
        contentValues.put(PLACE, subscription.getPlace());

        return contentValues;
    }

    @Override
    protected Subscription fromCursor(Cursor cursor) {
        return Subscription.builder()
                .id(getLong(cursor, ID))
                .name(getString(cursor, NAME))
                .image(getString(cursor, IMAGE))
                .date(getDate(cursor, DATE))
                .place(getString(cursor, PLACE))
                .build();
    }
}
