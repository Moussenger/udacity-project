package edu.udacity.mou.meeckets.presentation.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by mou on 11/11/17.
 */

public abstract class MeecketsActivity<P extends MeecketsPresenter, VM extends MeecketsViewModel<P>> extends AppCompatActivity {
    private VM viewModel;

//    @Inject
//    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
//
//    @Override
//    public AndroidInjector<Fragment> supportFragmentInjector() {
//        return fragmentDispatchingAndroidInjector;
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(layout());
        loadViewModel();
        loadPresenter();
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

    protected void loadPresenter() {
        if (viewModel.presenter() == null) {
            viewModel.presenter(createPresenter());
        }
    }

    @SuppressWarnings("unchecked")
    protected void configPresenter() {
        getPresenter().attachView(this);
        getPresenter().attachLifecycle(getLifecycle());
        getPresenter().attachViewModel(getViewModel());
    }

    protected P getPresenter() {
        return viewModel.presenter();
    }

    protected VM getViewModel() {
        return viewModel;
    }

    protected void init() {
        // Nothing to do here. Just for extends creation in childs.
    }

    protected abstract int layout();

    protected abstract Class<VM> viewModel();

    protected abstract P createPresenter();

    protected abstract String getTag();
}
