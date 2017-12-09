package edu.udacity.mou.meeckets.domain.repositories.tournaments;


import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mou on 11/22/17.
 */

public interface ISubscriptionsRepository {
    Observable<String> getSubscriptions();

    Single<Subscription> getSubscription(long id);

    Completable addSubscription(Subscription subscription);

    Completable deleteSubscription(long id);

    Completable deleteSubscriptions();

}
