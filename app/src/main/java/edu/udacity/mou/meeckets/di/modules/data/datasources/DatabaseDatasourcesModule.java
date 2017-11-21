package edu.udacity.mou.meeckets.di.modules.data.datasources;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.data.datasources.database.MeecketsDatabaseDatasource;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.UserStorageMapper;
import edu.udacity.mou.meeckets.domain.model.auth.User;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class DatabaseDatasourcesModule {

    @Provides
    public StorageMapper<User> provideUserStorageMapper() {
        return new UserStorageMapper();
    }

    @Provides
    public MeecketsDatabaseDatasource provideMeecketsDatabaseDatasource(Context context, StorageMapper<User> userStorageMapper) {
        return new MeecketsDatabaseDatasource(context, userStorageMapper);
    }
}
