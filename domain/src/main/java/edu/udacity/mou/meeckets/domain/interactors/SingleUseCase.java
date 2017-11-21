package edu.udacity.mou.meeckets.domain.interactors;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;

/**
 * Created by mou on 11/22/17.
 */

public abstract class SingleUseCase<T extends Object, P> extends UseCase<P, Single<T>, SingleEmitter<T>> {
    public SingleUseCase(Scheduler backgroundThread, Scheduler mainThread) {
        super(backgroundThread, mainThread);
    }

    public Single<T> run(final P parameter) {
        return (Single<T>) Single
                .create(emitter -> onRun((SingleEmitter<T>) emitter, parameter))
                .subscribeOn(backgroundThread)
                .observeOn(mainThread);
    }

}
