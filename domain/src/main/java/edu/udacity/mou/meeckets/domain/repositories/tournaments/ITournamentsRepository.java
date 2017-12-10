package edu.udacity.mou.meeckets.domain.repositories.tournaments;


import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mou on 11/22/17.
 */

public interface ITournamentsRepository {
    Observable<String> getTournaments();

    Single<Tournament> getTournament(long id);
}
