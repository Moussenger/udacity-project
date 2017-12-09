package edu.udacity.mou.meeckets.di.modules.data.datasources;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.data.datasources.database.MeecketsDatabaseDatasource;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.SubscriptionsStorageMapper;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.TournamentsStorageMapper;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.UserStorageMapper;
import edu.udacity.mou.meeckets.domain.model.auth.User;
import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;

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
    public StorageMapper<Tournament> provideTournamentStorageMapper() {
        return new TournamentsStorageMapper();
    }

    @Provides
    public StorageMapper<Subscription> provideSubscriptionStorageMapper() {
        return new SubscriptionsStorageMapper();
    }

    @Provides
    public MeecketsDatabaseDatasource provideMeecketsDatabaseDatasource(Context context, StorageMapper<User> userStorageMapper,
                                                                        StorageMapper<Tournament> tournamentStorageMapper,
                                                                        StorageMapper<Subscription> subscriptionStorageMapper) {
        return new MeecketsDatabaseDatasource(context, userStorageMapper, tournamentStorageMapper, subscriptionStorageMapper);
    }
}
