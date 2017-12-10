package edu.udacity.mou.meeckets.data.repositories;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.udacity.mou.meeckets.data.datasources.database.IMeecketsDatabaseDatasource;
import edu.udacity.mou.meeckets.data.datasources.network.tournaments.ITournamentsNetworkDatasource;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ITournamentsRepository;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;

/**
 * Created by mou on 11/9/17.
 */

@Singleton
public class TournamentRepository implements ITournamentsRepository {
    private ITournamentsNetworkDatasource tournamentsNetworkDatasource;
    private IMeecketsDatabaseDatasource databaseDatasource;

    @Inject
    public TournamentRepository(ITournamentsNetworkDatasource tournamentsNetworkDatasource,
                                IMeecketsDatabaseDatasource databaseDatasource) {
        this.tournamentsNetworkDatasource = tournamentsNetworkDatasource;
        this.databaseDatasource = databaseDatasource;
    }

    @Override
    public Observable<String> getTournaments() {
        return Observable.create(this::loadTournaments);
    }

    @Override
    public Single<Tournament> getTournament(long id) {
        return Single.create(emitter -> this.retrieveTournament(emitter, id));
    }

    private void loadTournaments(ObservableEmitter<String> emitter) {
        try {
            emitter.onNext(databaseDatasource.getTournaments().toString());
            List<Tournament> tournaments = tournamentsNetworkDatasource.tournaments();
            databaseDatasource.saveTournaments(tournaments);
            emitter.onComplete();
        } catch (Exception e) {
            emitter.onError(e);
        }
    }

    private void retrieveTournament(SingleEmitter<Tournament> emitter, long id) {
        try {
            emitter.onSuccess(databaseDatasource.getTournament(id));
        } catch (Exception e) {
            emitter.onError(e);
        }
    }
}
