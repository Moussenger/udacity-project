package edu.udacity.mou.meeckets.data.datasources.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import javax.inject.Singleton;

import edu.udacity.mou.meeckets.data.datasources.database.daos.AuthDao;
import edu.udacity.mou.meeckets.data.model.entities.AuthEntity;

/**
 * Created by mou on 11/14/17.
 */

@Database(entities = {AuthEntity.class}, version = 1)
@Singleton
public abstract class MeecketsDatabaseDatasource extends RoomDatabase{
    public abstract AuthDao authDao();
}
