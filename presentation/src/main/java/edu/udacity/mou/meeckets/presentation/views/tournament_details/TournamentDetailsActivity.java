package edu.udacity.mou.meeckets.presentation.views.tournament_details;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.presentation.MeecketsToolbarActivity;
import edu.udacity.mou.meeckets.presentation.R;
import edu.udacity.mou.meeckets.presentation.R2;

/**
 * Created by mou on 11/21/17.
 */

public class TournamentDetailsActivity extends MeecketsToolbarActivity<TournamentDetailsPresenter, TournamentDetailsViewModel> {
    public static final String TOURNAMENT_KEY = "Tournament";

    public static void launch(Activity activity, Tournament tournament) {
        Intent intent = new Intent(activity, TournamentDetailsActivity.class);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle();
        bundle.putSerializable(TOURNAMENT_KEY, tournament);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @BindView(R2.id.tournament_description_text)
    TextView descriptionTextView;

    @BindView(R2.id.tournament_picture_image)
    ImageView pictureImageView;

    @Inject
    TournamentDetailsPresenter presenter;

    @Override
    protected void init() {
        super.init();
        enableBackButton();
        getViewModel().tournament().observe(this, this::onTournamentAdded);
    }

    @Override
    protected int layout() {
        return R.layout.activity_tournament_details;
    }

    @Override
    protected Class<TournamentDetailsViewModel> viewModel() {
        return TournamentDetailsViewModel.class;
    }

    @Override
    protected TournamentDetailsPresenter createPresenter() {
        return presenter;
    }

    @Override
    protected String getTag() {
        return TournamentDetailsActivity.class.getSimpleName();
    }

    protected void onTournamentAdded(Tournament tournament) {
        if (tournament != null) {
            setToolbarTitle(tournament.getName());
            descriptionTextView.setText(tournament.getDescription());

            Picasso.with(this)
                    .load(tournament.getImage())
                    .resize(600, 400)
                    .centerCrop()
                    .into(pictureImageView);
        }
    }
}
