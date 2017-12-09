package edu.udacity.mou.meeckets.di.modules.device;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.device.accounts.location.MeecketsLocationProvider;
import edu.udacity.mou.meeckets.domain.device.IMeecketsLocationProvider;

/**
 * Created by mou on 11/21/17.
 */

@Module
public class LocationModule {
    @Provides
    public IMeecketsLocationProvider provideMeecketsLocationProvider(Context context) {
        return new MeecketsLocationProvider(context);
    }
}
