package edu.udacity.mou.meeckets.presentation;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by mou on 11/11/17.
 */

public abstract class MeecketsActivity<P extends MeecketsPresenter, VM extends ViewModel> extends AppCompatActivity implements HasSupportFragmentInjector {
    private VM viewModel;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(layout());
        loadViewModel();
        configPresenter();
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        getPresenter().detachLifecycle(getLifecycle());
        getPresenter().detachView();
        super.onDestroy();
    }

    protected void loadViewModel() {
        viewModel = ViewModelProviders.of(this).get(viewModel());
    }

    protected void init() {
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
