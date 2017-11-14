package edu.udacity.mou.meeckets.di.modules.domain;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mou on 11/11/17.
 */

@Module
public class SchedulersModule {
    @Provides
    @Named("background")
    Scheduler provideBackgroundThread() {
        return Schedulers.io();
    }

    @Provides
    @Named("main")
    Scheduler provideMainThread() {
        return AndroidSchedulers.mainThread();
    }
}
