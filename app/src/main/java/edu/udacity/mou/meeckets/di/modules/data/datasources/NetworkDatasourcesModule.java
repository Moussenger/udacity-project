package edu.udacity.mou.meeckets.di.modules.data.datasources;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.data.datasources.network.auth.AuthNetworkDatasource;
import edu.udacity.mou.meeckets.data.datasources.network.auth.IAuthNetworkDatasource;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class NetworkDatasourcesModule {
    @Provides
    public IAuthNetworkDatasource provideAuthNetworkDatasource() {
        return new AuthNetworkDatasource();
    }
}
