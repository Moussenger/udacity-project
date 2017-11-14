package edu.udacity.mou.meeckets.domain.interactors;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.Scheduler;

/**
 * Created by mou on 11/11/17.
 */

public abstract class CompletableUseCase<P> extends UseCase<P, Completable, CompletableEmitter> {
    public CompletableUseCase(Scheduler backgroundThread, Scheduler mainThread) {
        super(backgroundThread, mainThread);
    }

    public Completable run(final P parameter) {
        return Completable
                .create(emitter -> onRun(emitter, parameter))
                .subscribeOn(backgroundThread)
                .observeOn(mainThread);
    }

}
