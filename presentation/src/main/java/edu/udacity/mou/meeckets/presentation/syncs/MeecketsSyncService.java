package edu.udacity.mou.meeckets.presentation.syncs;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by mou on 12/10/17.
 */

public class MeecketsSyncService extends Service {
    private static final Object miTrackerSyncServiceLock = new Object();
    private static MeecketsSyncAdapter syncAdapter = null;

    @Inject
    MeecketsSyncAdapter adapter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return syncAdapter.getSyncAdapterBinder();
    }

    @Override
    public void onCreate() {
        synchronized (miTrackerSyncServiceLock) {
            if (syncAdapter == null) {
                AndroidInjection.inject(this);
                syncAdapter = adapter;
            }
        }
    }

}
