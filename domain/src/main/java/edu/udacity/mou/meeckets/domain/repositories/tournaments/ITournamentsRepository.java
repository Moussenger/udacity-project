package edu.udacity.mou.meeckets.domain.repositories.tournaments;


import io.reactivex.Observable;

/**
 * Created by mou on 11/22/17.
 */

public interface ITournamentsRepository {
    Observable<String> getTournaments();
}
