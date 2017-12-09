package edu.udacity.mou.meeckets.data.repositories;


import javax.inject.Inject;
import javax.inject.Singleton;

import edu.udacity.mou.meeckets.data.datasources.database.IMeecketsDatabaseDatasource;
import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ISubscriptionsRepository;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;

/**
 * Created by mou on 11/9/17.
 */

@Singleton
public class SubscriptionsRepository implements ISubscriptionsRepository {
    private IMeecketsDatabaseDatasource databaseDatasource;

    @Inject
    public SubscriptionsRepository(IMeecketsDatabaseDatasource databaseDatasource) {
        this.databaseDatasource = databaseDatasource;
    }

    @Override
    public Observable<String> getSubscriptions() {
        return Observable.create(this::loadSubscriptions);
    }

    @Override
    public Single<Subscription> getSubscription(long id) {
        return Single.create(emitter -> this.loadSubscription(emitter, id));
    }

    @Override
    public Completable addSubscription(Subscription subscription) {
        return Completable.create(emitter -> saveSubscription(emitter, subscription));
    }

    @Override
    public Completable deleteSubscription(long id) {
        return Completable.create(emitter -> removeSubscription(emitter, id));
    }

    @Override
    public Completable deleteSubscriptions() {
        return Completable.create(this::removeSubscriptions);
    }

    private void loadSubscriptions(ObservableEmitter<String> emitter) {
        try {
            emitter.onNext(databaseDatasource.getSubscriptions().toString());
            emitter.onComplete();
        } catch (Exception e) {
            emitter.onError(e);
        }
    }

    private void loadSubscription(SingleEmitter<Subscription> emitter, long id) {
        try {
            emitter.onSuccess(databaseDatasource.getSubscription(id));
        } catch (Exception e) {
            emitter.onError(e);
        }
    }

    private void saveSubscription(CompletableEmitter emitter, Subscription subscription) {
        try {
            databaseDatasource.addSubscription(subscription);
            emitter.onComplete();
        } catch (Exception e) {
            emitter.onError(e);
        }
    }

    private void removeSubscription(CompletableEmitter emitter, long id) {
        try {
            databaseDatasource.deleteSubscription(id);
            emitter.onComplete();
        } catch (Exception e) {
            emitter.onError(e);
        }
    }

    private void removeSubscriptions(CompletableEmitter emitter) {
        try {
            databaseDatasource.deleteSubscriptions();
            emitter.onComplete();
        } catch (Exception e) {
            emitter.onError(e);
        }
    }
}
