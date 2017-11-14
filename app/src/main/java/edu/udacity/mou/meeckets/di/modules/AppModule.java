package edu.udacity.mou.meeckets.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mou on 11/11/17.
 */

@Module
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }
}
