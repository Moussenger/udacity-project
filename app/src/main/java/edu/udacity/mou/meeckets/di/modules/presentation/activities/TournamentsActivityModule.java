package edu.udacity.mou.meeckets.di.modules.presentation.activities;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetTournaments;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.UpdateTournamentsLocation;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.presentation.views.tournaments.TournamentsActivity;
import edu.udacity.mou.meeckets.presentation.views.tournaments.TournamentsPresenter;
import edu.udacity.mou.meeckets.presentation.views.tournaments.adapters.TournamentsAdapter;

/**
 * Created by mou on 11/13/17.
 */

@Module
public class TournamentsActivityModule {
    @Provides
    TournamentsPresenter provideTournamentsPresenter(CheckLogin checkLogin, GetTournaments getTournaments) {
        return new TournamentsPresenter(checkLogin, getTournaments);
    }

    @Provides
    TournamentsAdapter provideTournamentsAdapter(TournamentsActivity context, StorageMapper<Tournament> tournamentStorageMapper,
                                                 UpdateTournamentsLocation updateTournamentsLocation) {
        return new TournamentsAdapter(context, tournamentStorageMapper, updateTournamentsLocation);
    }
}
