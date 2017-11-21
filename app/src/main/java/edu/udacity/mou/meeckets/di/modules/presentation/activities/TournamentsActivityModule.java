package edu.udacity.mou.meeckets.di.modules.presentation.activities;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.presentation.views.tournaments.TournamentsPresenter;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class TournamentsActivityModule {
    @Provides
    TournamentsPresenter provideTournamentsPresenter() {
        return new TournamentsPresenter();
    }
}
