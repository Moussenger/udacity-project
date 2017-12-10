package edu.udacity.mou.meeckets.presentation.views.tournaments;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import edu.udacity.mou.meeckets.presentation.MeecketsToolbarActivity;
import edu.udacity.mou.meeckets.presentation.R;
import edu.udacity.mou.meeckets.presentation.R2;
import edu.udacity.mou.meeckets.presentation.views.tournaments.adapters.TournamentsAdapter;

/**
 * Created by mou on 11/21/17.
 */

public class TournamentsActivity extends MeecketsToolbarActivity<TournamentsPresenter, TournamentsViewModel> {
    public static void launchClear(Activity activity) {
        Intent intent = new Intent(activity, TournamentsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle();
        activity.startActivity(intent, bundle);
    }

    @Inject
    TournamentsPresenter presenter;

    @Inject
    TournamentsAdapter adapter;

    @BindView(R2.id.tournaments_list_recycler)
    RecyclerView tournamentsRecyclerView;

    @Override
    protected void init() {
        super.init();
        configRecyclerView();
        getViewModel().tournamentsCursor().observe(this, this::loadCursor);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tournaments_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.item_profile) {
            getPresenter().onProfileClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void removeListener() {
        adapter.setListener(null);
    }

    private void configRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),
                getResources().getInteger(R.integer.tournamentsSpanCount));

        adapter.setListener(getPresenter()::onTournamentClicked);
        tournamentsRecyclerView.setHasFixedSize(true);
        tournamentsRecyclerView.setLayoutManager(layoutManager);
        tournamentsRecyclerView.setAdapter(adapter);
    }

    private void loadCursor(Cursor cursor) {
        adapter.setCursor(cursor);
    }
}
