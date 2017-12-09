package edu.udacity.mou.meeckets.domain.interactors.tournaments;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.CompletableUseCase;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ISubscriptionsRepository;
import io.reactivex.CompletableEmitter;
import io.reactivex.Scheduler;

/**
 * Created by mou on 11/11/17.
 */

public class DeleteSubscriptions extends CompletableUseCase<Void> {
    private ISubscriptionsRepository subscriptionsRepository;

    @Inject
    public DeleteSubscriptions(Scheduler backgroundThread, Scheduler mainThread,
                               ISubscriptionsRepository subscriptionsRepository) {
        super(backgroundThread, mainThread);
        this.subscriptionsRepository = subscriptionsRepository;
    }

    @Override
    protected void onRun(CompletableEmitter emitter, Void parameter) {
        subscriptionsRepository.deleteSubscriptions().subscribe(
                emitter::onComplete,
                emitter::onError
        );
    }
}
