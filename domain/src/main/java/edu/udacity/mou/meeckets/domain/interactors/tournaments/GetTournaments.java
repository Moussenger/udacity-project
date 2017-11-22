package edu.udacity.mou.meeckets.domain.interactors.tournaments;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.ObservableUseCase;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ITournamentsRepository;
import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;

/**
 * Created by mou on 11/11/17.
 */

public class GetTournaments extends ObservableUseCase<String, Void> {
    private ITournamentsRepository tournamentsRepository;

    @Inject
    public GetTournaments(Scheduler backgroundThread, Scheduler mainThread,
                          ITournamentsRepository tournamentsRepository) {
        super(backgroundThread, mainThread);
        this.tournamentsRepository = tournamentsRepository;
    }

    @Override
    protected void onRun(ObservableEmitter<String> emitter, Void parameter) {
        tournamentsRepository.getTournaments().subscribe(
                emitter::onNext,
                emitter::onError,
                emitter::onComplete
        );
    }
}
