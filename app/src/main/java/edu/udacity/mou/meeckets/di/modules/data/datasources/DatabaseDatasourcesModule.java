package edu.udacity.mou.meeckets.di.modules.data.datasources;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.data.datasources.database.MeecketsDatabaseDatasource;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class DatabaseDatasourcesModule {
    @Provides
    @Named("database")
    public Class<MeecketsDatabaseDatasource> provideDatabase() {
        return MeecketsDatabaseDatasource.class;
    }

    @Provides
    @Named("databaseName")
    public String provideDatabaseName() {
        return "meeckets";
    }

    @Provides
    public MeecketsDatabaseDatasource provideMeecketsDatabaseDatasource(Context context,
                                                                        @Named("database") Class<MeecketsDatabaseDatasource> database,
                                                                        @Named("databaseName") String databaseName) {
        return Room.databaseBuilder(context, database, databaseName).build();

    }
}
