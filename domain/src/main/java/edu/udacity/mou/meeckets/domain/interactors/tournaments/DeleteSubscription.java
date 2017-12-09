package edu.udacity.mou.meeckets.domain.interactors.tournaments;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.CompletableUseCase;
import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ISubscriptionsRepository;
import io.reactivex.CompletableEmitter;
import io.reactivex.Scheduler;

/**
 * Created by mou on 11/11/17.
 */

public class DeleteSubscription extends CompletableUseCase<Subscription> {
    private ISubscriptionsRepository subscriptionsRepository;

    @Inject
    public DeleteSubscription(Scheduler backgroundThread, Scheduler mainThread,
                              ISubscriptionsRepository subscriptionsRepository) {
        super(backgroundThread, mainThread);
        this.subscriptionsRepository = subscriptionsRepository;
    }

    @Override
    protected void onRun(CompletableEmitter emitter, Subscription subscription) {
        subscriptionsRepository.deleteSubscription(subscription.getId()).subscribe(
                emitter::onComplete,
                emitter::onError
        );
    }
}
