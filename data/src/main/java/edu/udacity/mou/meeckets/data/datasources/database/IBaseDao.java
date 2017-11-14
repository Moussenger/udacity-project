package edu.udacity.mou.meeckets.data.datasources.database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

/**
 * Created by mou on 11/14/17.
 */

public interface IBaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(T... data);

    @Update
    int update(T data);

    @Update
    int update(T... data);

    @Delete
    int delete(T data);

    @Delete
    int delete(T... data);
}
