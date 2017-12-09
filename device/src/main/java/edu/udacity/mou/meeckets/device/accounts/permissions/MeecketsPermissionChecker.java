package edu.udacity.mou.meeckets.device.accounts.permissions;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.udacity.mou.meeckets.domain.device.IMeecketsPermissionChecker;

/**
 * Created by mou on 12/9/17.
 */

@Singleton
public class MeecketsPermissionChecker implements IMeecketsPermissionChecker {
    private Context context;

    @Inject
    public MeecketsPermissionChecker(Context context) {
        this.context = context;
    }

    @Override
    public boolean hasLocationPermission() {
        return hasPermission(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }
}
