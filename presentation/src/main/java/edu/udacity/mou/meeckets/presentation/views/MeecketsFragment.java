package edu.udacity.mou.meeckets.presentation.views;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by mou on 11/11/17.
 */

public abstract class MeecketsFragment<P extends MeecketsPresenter, VM extends ViewModel> extends Fragment {
    private VM viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        attachComponents();
        View view = inflater.inflate(layout(), container, false);
        ButterKnife.bind(this, view);
        init(view);

        return view;
    }

    @Override
    public void onDestroyView() {
        detachComponents();
        super.onDestroyView();
    }

    protected void attachComponents() {
        AndroidSupportInjection.inject(this);
        loadViewModel();
        configPresenter();
    }

    protected void detachComponents() {
        getPresenter().detachLifecycle(getLifecycle());
        getPresenter().detachView();
        super.onDetach();
    }

    protected void loadViewModel() {
        viewModel = ViewModelProviders.of(this).get(viewModel());
    }

    protected void init(View view) {
        // Nothing to do here. Just for extends creation in childs.
    }

    @SuppressWarnings("unchecked")
    protected void configPresenter() {
        getPresenter().attachLifecycle(getLifecycle());
        getPresenter().attachViewModel(getViewModel());
    }

    protected VM getViewModel() {
        return viewModel;
    }

    protected abstract int layout();

    protected abstract Class<VM> viewModel();

    protected abstract P getPresenter();

}
