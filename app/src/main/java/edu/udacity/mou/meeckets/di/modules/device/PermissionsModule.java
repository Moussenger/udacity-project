package edu.udacity.mou.meeckets.di.modules.device;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.device.accounts.permissions.MeecketsPermissionChecker;
import edu.udacity.mou.meeckets.domain.device.IMeecketsPermissionChecker;

/**
 * Created by mou on 11/21/17.
 */

@Module
public class PermissionsModule {
    @Provides
    public IMeecketsPermissionChecker providerMeecketsPermissionChecker(Context context) {
        return new MeecketsPermissionChecker(context);
    }
}
