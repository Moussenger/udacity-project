package edu.udacity.mou.meeckets.domain.interactors.tournaments;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.SingleUseCase;
import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ISubscriptionsRepository;
import io.reactivex.Scheduler;
import io.reactivex.SingleEmitter;

/**
 * Created by mou on 11/11/17.
 */

public class GetSubscription extends SingleUseCase<Subscription, Long> {
    private ISubscriptionsRepository subscriptionsRepository;

    @Inject
    public GetSubscription(Scheduler backgroundThread, Scheduler mainThread,
                           ISubscriptionsRepository subscriptionsRepository) {
        super(backgroundThread, mainThread);
        this.subscriptionsRepository = subscriptionsRepository;
    }

    @Override
    protected void onRun(SingleEmitter<Subscription> emitter, Long id) {
        subscriptionsRepository.getSubscription(id).subscribe(
                emitter::onSuccess,
                emitter::onError
        );
    }
}
