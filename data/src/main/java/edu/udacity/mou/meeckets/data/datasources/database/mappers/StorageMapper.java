package edu.udacity.mou.meeckets.data.datasources.database.mappers;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mou on 11/21/17.
 */

public abstract class StorageMapper<T> {

    public ContentValues[] values(final List<T> objects) {
        List<ContentValues> contentValues = new ArrayList<>();

        for (T object : objects) {
            contentValues.addAll(Arrays.asList(fromValues(object)));
        }

        return contentValues.toArray(new ContentValues[contentValues.size()]);
    }

    public ContentValues values(T object) {
        return fromValues(object);
    }

    public T single(Cursor cursor) {
        if (!cursor.moveToFirst()) {
            return null;
        }

        return fromCursor(cursor);
    }

    public List<T> all(Cursor cursor) {
        ArrayList<T> data = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                data.add(fromCursor(cursor));
            } while (cursor.moveToNext());
        }

        return data;
    }

    protected String getString(Cursor cursor, String name) {
        return cursor.getString(cursor.getColumnIndex(name));
    }

    protected abstract ContentValues fromValues(T object);

    protected abstract T fromCursor(Cursor cursor);

}
