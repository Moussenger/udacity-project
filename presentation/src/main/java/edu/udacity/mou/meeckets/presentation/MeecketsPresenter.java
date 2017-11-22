package edu.udacity.mou.meeckets.presentation;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;

/**
 * Created by mou on 11/11/17.
 */

public abstract class MeecketsPresenter<V extends MeecketsActivity, VM extends ViewModel> implements DefaultLifecycleObserver {
    private WeakReference<V> viewReference;
    private VM viewModel;

    protected CheckLogin checkLogin;

    public MeecketsPresenter(CheckLogin checkLogin) {
        this.checkLogin = checkLogin;
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
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

    public void attachView(V view) {
        detachView();
        viewReference = new WeakReference<>(view);
    }

    public void detachView() {
        if (viewReference != null) {
            viewReference.clear();
        }
    }

    public void onViewModelCleared() {
        viewModel = null;
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
