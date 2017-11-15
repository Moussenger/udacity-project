package edu.udacity.mou.meeckets.presentation;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by mou on 11/11/17.
 */

public abstract class MeecketsPresenter<V, VM extends ViewModel> implements DefaultLifecycleObserver {
    private WeakReference<V> viewReference;
    private VM viewModel;
    private CompositeDisposable compositeDisposable;

    public MeecketsPresenter(V view) {
        viewReference = new WeakReference<>(view);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        compositeDisposable.clear();
        detachView();
    }

    public void attachViewModel(VM viewModel) {
        this.viewModel = viewModel;
    }

    public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    public void detachView() {
        viewReference.clear();
    }

    public void disposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    protected V getView() {
        return viewReference != null ? viewReference.get() : null;
    }

    protected VM getViewModel() {
        return viewModel;
    }

    protected boolean isViewAttached() {
        return viewReference != null && viewReference.get() != null;
    }

    protected boolean isViewAttached(V view) {
        return view != null;
    }
}
