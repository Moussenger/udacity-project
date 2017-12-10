package edu.udacity.mou.meeckets.di.modules.presentation.services;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetTournaments;
import edu.udacity.mou.meeckets.presentation.syncs.MeecketsSyncAdapter;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class AdapterServiceModule {
    @Provides
    MeecketsSyncAdapter provideMeecketsSyncAdapter(Context context, GetTournaments getTournaments) {
        return new MeecketsSyncAdapter(context, true, getTournaments);
    }
}
