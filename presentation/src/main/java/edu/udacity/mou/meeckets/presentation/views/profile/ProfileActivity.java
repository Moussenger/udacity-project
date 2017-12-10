package edu.udacity.mou.meeckets.presentation.views.profile;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import edu.udacity.mou.meeckets.domain.model.auth.User;
import edu.udacity.mou.meeckets.presentation.MeecketsToolbarActivity;
import edu.udacity.mou.meeckets.presentation.R;
import edu.udacity.mou.meeckets.presentation.R2;
import edu.udacity.mou.meeckets.presentation.views.profile.adapters.SubscriptionsAdapter;

/**
 * Created by mou on 11/11/17.
 */

public class ProfileActivity extends MeecketsToolbarActivity<ProfilePresenter, ProfileViewModel> {
    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ProfileActivity.class);
        activity.startActivity(intent);
    }

    @Inject
    ProfilePresenter presenter;

    @Inject
    SubscriptionsAdapter adapter;

    @BindView(R2.id.profile_username_text)
    TextView usernameTextView;

    @BindView(R2.id.profile_subscriptions_list)
    RecyclerView subscriptionsRecyclerView;

    @Override
    public int layout() {
        return R.layout.activity_profile;
    }

    @Override
    protected void init() {
        super.init();
        enableBackButton();
        configRecyclerView();
        getViewModel().user().observe(this, this::onUserLoaded);
        getViewModel().subscriptionsCursor().observe(this, this::loadCursor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.item_logout) {
            presenter.onLogoutClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Class<ProfileViewModel> viewModel() {
        return ProfileViewModel.class;
    }

    @Override
    protected ProfilePresenter createPresenter() {
        return presenter;
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    private void configRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        //adapter.setListener(getPresenter()::onTournamentClicked);
        subscriptionsRecyclerView.setHasFixedSize(true);
        subscriptionsRecyclerView.setLayoutManager(layoutManager);
        subscriptionsRecyclerView.setAdapter(adapter);
    }

    private void onUserLoaded(User user) {
        usernameTextView.setText(user.getUsername());
        setToolbarTitle(user.getUsername());
    }

    private void loadCursor(Cursor cursor) {
        adapter.setCursor(cursor);
    }

}
