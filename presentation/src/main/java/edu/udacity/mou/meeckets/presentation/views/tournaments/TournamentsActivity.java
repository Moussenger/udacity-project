package edu.udacity.mou.meeckets.presentation.views.tournaments;

import android.content.Context;
import android.content.Intent;
import android.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import edu.udacity.mou.meeckets.presentation.R;
import edu.udacity.mou.meeckets.presentation.R2;
import edu.udacity.mou.meeckets.presentation.views.MeecketsActivity;

/**
 * Created by mou on 11/21/17.
 */

public class TournamentsActivity extends MeecketsActivity<TournamentsPresenter, TournamentsViewModel> {
    public static void launch(Context context) {
        context.startActivity(new Intent(context, TournamentsActivity.class));
    }

    public static void launchClear(Context context) {
        Intent intent = new Intent(context, TournamentsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Inject
    TournamentsPresenter presenter;

    @BindView(R2.id.tournaments_toolbar)
    Toolbar toolbar;

    @Override
    protected void init() {
        setActionBar(toolbar);
        getActionBar().setTitle("");
    }

    @Override
    protected int layout() {
        return R.layout.activity_tournaments;
    }

    @Override
    protected Class<TournamentsViewModel> viewModel() {
        return TournamentsViewModel.class;
    }

    @Override
    protected TournamentsPresenter createPresenter() {
        return presenter;
    }

    @Override
    protected String getTag() {
        return TournamentsActivity.class.getSimpleName();
    }
}
