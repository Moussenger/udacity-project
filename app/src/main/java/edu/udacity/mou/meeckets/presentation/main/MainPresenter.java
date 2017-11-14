package edu.udacity.mou.meeckets.presentation.main;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.presentation.MeecketsPresenter;

/**
 * Created by mou on 11/11/17.
 */

public class MainPresenter extends MeecketsPresenter<MainActivity, ViewModel> {


    @Inject
    public MainPresenter(MainActivity mainActivity) {
        super(mainActivity);
    }

}
