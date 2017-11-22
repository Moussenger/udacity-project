package edu.udacity.mou.meeckets.domain.interactors;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;

/**
 * Created by mou on 11/22/17.
 */

public abstract class ObservableUseCase<T, P> extends UseCase<P, Observable<T>, ObservableEmitter<T>> {

    public ObservableUseCase(Scheduler backgroundThread, Scheduler mainThread) {
        super(backgroundThread, mainThread);
    }

    public Observable<T> run(final P parameter) {
        return (Observable<T>) Observable
                .create(emitter -> onRun((ObservableEmitter<T>) emitter, parameter))
                .subscribeOn(backgroundThread)
                .observeOn(mainThread);
    }
}
