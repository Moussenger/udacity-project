package edu.udacity.mou.meeckets.presentation;

import android.widget.Toolbar;

import butterknife.BindView;

/**
 * Created by mou on 11/21/17.
 */

public abstract class MeecketsToolbarActivity<P extends MeecketsPresenter, VM extends MeecketsViewModel<P>> extends MeecketsActivity<P, VM> {
    @BindView(R2.id.meeckets_toolbar)
    Toolbar toolbar;

    @Override
    protected void init() {
        setActionBar(toolbar);
        getActionBar().setTitle("");
    }
}
