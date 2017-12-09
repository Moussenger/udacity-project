package edu.udacity.mou.meeckets.presentation;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by mou on 11/11/17.
 */

public abstract class MeecketsActivity<P extends MeecketsPresenter, VM extends MeecketsViewModel<P>> extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;

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
        setupWindowTransitions();
        init();
    }

    @Override
    protected void onDestroy() {
        getPresenter().detachLifecycle(getLifecycle());
        getPresenter().detachView();
        removeActivityFromTransitionManager();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean result = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;

        switch (requestCode) {
            case REQUEST_LOCATION:
                onLocationPermissionResponse(result);
                break;
        }
    }

    public void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
    }

    /**
     * Remove memory leaks for Lollipop transitions
     */
    private void removeActivityFromTransitionManager() {
        Class transitionManagerClass = TransitionManager.class;

        try {
            Field runningTransitionsField = transitionManagerClass.getDeclaredField("sRunningTransitions");
            runningTransitionsField.setAccessible(true);

            ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> runningTransitions =
                    (ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>>) runningTransitionsField.get(transitionManagerClass);

            if (runningTransitions.get() != null && runningTransitions.get().get() != null) {
                ArrayMap map = runningTransitions.get().get();
                View decorView = getWindow().getDecorView();

                if (map.containsKey(decorView)) {
                    map.remove(decorView);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
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

    protected void setupWindowTransitions() {
        // Nothing to do here. Just for setup transitions in childs.
    }

    protected void init() {
        // Nothing to do here. Just for extends creation in childs.
    }

    protected void onLocationPermissionResponse(boolean granted) {
        // Nothing to do here. Just for extends creation in childs.
    }

    protected abstract int layout();

    protected abstract Class<VM> viewModel();

    protected abstract P createPresenter();

    protected abstract String getTag();
}
