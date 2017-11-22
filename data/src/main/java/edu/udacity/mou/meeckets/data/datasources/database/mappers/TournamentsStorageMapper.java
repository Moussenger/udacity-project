package edu.udacity.mou.meeckets.data.datasources.database.mappers;

import android.content.ContentValues;
import android.database.Cursor;

import edu.udacity.mou.meeckets.domain.model.tournaments.Location;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;

import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentColumns.DATE;
import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentColumns.DESCRIPTION;
import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentColumns.ID;
import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentColumns.IMAGE;
import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentColumns.LATITUDE;
import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentColumns.LONGITUDE;
import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentColumns.NAME;
import static edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentColumns.PLACE;

/**
 * Created by mou on 11/21/17.
 */

public class TournamentsStorageMapper extends StorageMapper<Tournament> {

    @Override
    protected ContentValues fromValues(Tournament tournament) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, tournament.getId());
        contentValues.put(NAME, tournament.getName());
        contentValues.put(IMAGE, tournament.getImage());
        contentValues.put(DESCRIPTION, tournament.getDescription());
        contentValues.put(DATE, convertDate(tournament.getDate()));
        contentValues.put(LATITUDE, tournament.getLocation().getLatitude());
        contentValues.put(LONGITUDE, tournament.getLocation().getLongitude());
        contentValues.put(PLACE, tournament.getLocation().getName());

        return contentValues;
    }

    @Override
    protected Tournament fromCursor(Cursor cursor) {
        Location location = Location.builder()
                .name(getString(cursor, PLACE))
                .latitude(getFloat(cursor, LATITUDE))
                .longitude(getFloat(cursor, LONGITUDE))
                .build();

        return Tournament.builder()
                .id(getLong(cursor, ID))
                .name(getString(cursor, NAME))
                .image(getString(cursor, IMAGE))
                .description(getString(cursor, DESCRIPTION))
                .date(getDate(cursor, DATE))
                .location(location)
                .build();
    }
}
