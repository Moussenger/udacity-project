package edu.udacity.mou.meeckets.domain.interactors.tournaments;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.SingleUseCase;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ITournamentsRepository;
import io.reactivex.Scheduler;
import io.reactivex.SingleEmitter;

/**
 * Created by mou on 11/11/17.
 */

public class GetTournament extends SingleUseCase<Tournament, Long> {
    private ITournamentsRepository tournamentsRepository;

    @Inject
    public GetTournament(Scheduler backgroundThread, Scheduler mainThread,
                         ITournamentsRepository tournamentsRepository) {
        super(backgroundThread, mainThread);
        this.tournamentsRepository = tournamentsRepository;
    }

    @Override
    protected void onRun(SingleEmitter<Tournament> emitter, Long id) {
        tournamentsRepository.getTournament(id).subscribe(
                emitter::onSuccess,
                emitter::onError
        );
    }
}
