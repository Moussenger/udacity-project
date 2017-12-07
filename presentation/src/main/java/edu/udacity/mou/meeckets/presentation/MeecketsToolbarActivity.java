package edu.udacity.mou.meeckets.presentation;


import android.widget.TextView;
import android.widget.Toolbar;

import butterknife.BindView;

/**
 * Created by mou on 11/21/17.
 */

public abstract class MeecketsToolbarActivity<P extends MeecketsPresenter, VM extends MeecketsViewModel<P>> extends MeecketsActivity<P, VM> {
    @BindView(R2.id.meeckets_toolbar)
    Toolbar toolbar;

    @BindView(R2.id.meeckets_toolbar_title)
    TextView toolbarTitle;

    @Override
    protected void init() {
        setActionBar(toolbar);
        getActionBar().setTitle("");
        toolbar.setNavigationOnClickListener((v) -> onBackPressed());
    }


    protected void enableBackButton() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
    }

    protected void setToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }
}
