package edu.udacity.mou.meeckets.di.modules.data.datasources;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.data.datasources.network.auth.IAuthNetworkDatasource;
import edu.udacity.mou.meeckets.data.datasources.network.auth.MockAuthNetworkDatasource;
import edu.udacity.mou.meeckets.data.datasources.network.tournaments.ITournamentsNetworkDatasource;
import edu.udacity.mou.meeckets.data.datasources.network.tournaments.MockTournamentsNetworkDatasource;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class NetworkDatasourcesModule {
    @Provides
    public IAuthNetworkDatasource provideAuthNetworkDatasource() {
        return new MockAuthNetworkDatasource();
    }

    @Provides
    public ITournamentsNetworkDatasource provideTournamentNetworkDatasource() {
        return new MockTournamentsNetworkDatasource();
    }
}
