package edu.udacity.mou.meeckets.presentation.main;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.presentation.MeecketsActivity;
import edu.udacity.mou.meeckets.presentation.R;

/**
 * Created by mou on 11/11/17.
 */

public class MainActivity extends MeecketsActivity<MainPresenter, MainViewModel> {
    @Inject
    MainPresenter presenter;

    @Override
    public int layout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment_container, MainFragment.newInstance())
                .commit();
    }

    @Override
    protected Class<MainViewModel> viewModel() {
        return MainViewModel.class;
    }

    @Override
    protected MainPresenter getPresenter() {
        return presenter;
    }


}
