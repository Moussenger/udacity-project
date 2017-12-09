package edu.udacity.mou.meeckets.domain.interactors.tournaments;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.CompletableUseCase;
import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ISubscriptionsRepository;
import io.reactivex.CompletableEmitter;
import io.reactivex.Scheduler;

/**
 * Created by mou on 11/11/17.
 */

public class AddSubscription extends CompletableUseCase<Tournament> {
    private ISubscriptionsRepository subscriptionsRepository;

    @Inject
    public AddSubscription(Scheduler backgroundThread, Scheduler mainThread,
                           ISubscriptionsRepository subscriptionsRepository) {
        super(backgroundThread, mainThread);
        this.subscriptionsRepository = subscriptionsRepository;
    }

    @Override
    protected void onRun(CompletableEmitter emitter, Tournament tournament) {
        Subscription subscription = createSubscription(tournament);

        subscriptionsRepository.addSubscription(subscription).subscribe(
                emitter::onComplete,
                emitter::onError
        );
    }

    private Subscription createSubscription(Tournament tournament) {
        return Subscription.builder()
                .id(tournament.getId())
                .image(tournament.getImage())
                .place(tournament.getLocation().getName())
                .date(tournament.getDate())
                .name(tournament.getName())
                .build();
    }
}
