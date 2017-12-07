package edu.udacity.mou.meeckets.presentation.views.tournament_details;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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
        loadMap();
        getViewModel().googleMap().observe(this, this::onMapLoaded);
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

    protected void loadMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.tournament_map_maps);
        mapFragment.getMapAsync(getPresenter()::onMapLoaded);
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

            drawLocation(tournament, getViewModel().googleMap().getValue());
        }
    }

    protected synchronized void onMapLoaded(GoogleMap googleMap) {
        drawLocation(getViewModel().tournament().getValue(), googleMap);
    }

    protected void drawLocation(Tournament tournament, GoogleMap googleMap) {
        if (tournament != null && googleMap != null) {
            LatLng location = new LatLng(tournament.getLocation().getLatitude(), tournament.getLocation().getLongitude());
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(location)
                    .zoom(15)
                    .bearing(0)
                    .tilt(45)
                    .build();

            googleMap.clear();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            googleMap.addMarker(new MarkerOptions().position(location).title(tournament.getLocation().getName()));
        }
    }
}
