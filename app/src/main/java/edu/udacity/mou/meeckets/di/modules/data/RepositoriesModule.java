package edu.udacity.mou.meeckets.di.modules.data;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.data.datasources.database.MeecketsDatabaseDatasource;
import edu.udacity.mou.meeckets.data.datasources.network.auth.IAuthNetworkDatasource;
import edu.udacity.mou.meeckets.data.datasources.network.tournaments.ITournamentsNetworkDatasource;
import edu.udacity.mou.meeckets.data.repositories.AuthRepository;
import edu.udacity.mou.meeckets.data.repositories.TournamentRepository;
import edu.udacity.mou.meeckets.domain.repositories.auth.IAuthRepository;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ITournamentsRepository;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class RepositoriesModule {
    @Provides
    public IAuthRepository provideAuthRepository(IAuthNetworkDatasource authNetworkDatasource,
                                                 MeecketsDatabaseDatasource databaseDatasource) {
        return new AuthRepository(authNetworkDatasource, databaseDatasource);
    }

    @Provides
    public ITournamentsRepository provideTournamentRepository(ITournamentsNetworkDatasource tournamentsNetworkDatasource,
                                                              MeecketsDatabaseDatasource databaseDatasource) {
        return new TournamentRepository(tournamentsNetworkDatasource, databaseDatasource);
    }
}
