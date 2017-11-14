package edu.udacity.mou.meeckets.domain.interactors;

import io.reactivex.Scheduler;

/**
 * Created by mou on 11/11/17.
 */

public abstract class UseCase<P, O, E> {
    protected Scheduler backgroundThread;
    protected Scheduler mainThread;

    public UseCase(Scheduler backgroundThread, Scheduler mainThread) {
        this.backgroundThread = backgroundThread;
        this.mainThread = mainThread;
    }

    public abstract O run(final P parameter);

    protected abstract void onRun(E emitter, P parameter);
}
