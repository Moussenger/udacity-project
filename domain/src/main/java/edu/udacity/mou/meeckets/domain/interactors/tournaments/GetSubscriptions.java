package edu.udacity.mou.meeckets.domain.interactors.tournaments;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.ObservableUseCase;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ISubscriptionsRepository;
import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;

/**
 * Created by mou on 11/11/17.
 */

public class GetSubscriptions extends ObservableUseCase<String, Void> {
    private ISubscriptionsRepository subscriptionsRepository;

    @Inject
    public GetSubscriptions(Scheduler backgroundThread, Scheduler mainThread,
                            ISubscriptionsRepository subscriptionsRepository) {
        super(backgroundThread, mainThread);
        this.subscriptionsRepository = subscriptionsRepository;
    }

    @Override
    protected void onRun(ObservableEmitter<String> emitter, Void parameter) {
        subscriptionsRepository.getSubscriptions().subscribe(
                emitter::onNext,
                emitter::onError,
                emitter::onComplete
        );
    }
}
