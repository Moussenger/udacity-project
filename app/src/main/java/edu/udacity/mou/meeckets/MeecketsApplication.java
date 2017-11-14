package edu.udacity.mou.meeckets;

import android.app.Activity;
import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import edu.udacity.mou.meeckets.di.components.DaggerMeecketsComponent;
import timber.log.Timber;

/**
 * Created by mou on 11/11/17.
 */

public class MeecketsApplication extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        if (canInitApp()) {
            configDI();
            configTimber();
            configStetho();
        }

    }

    private boolean canInitApp() {
        return setupLeakCanary() != RefWatcher.DISABLED;
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }

        return LeakCanary.install(this);
    }

    private void configDI() {
        DaggerMeecketsComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    private void configTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    private void configStetho() {
        Stetho.initializeWithDefaults(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
