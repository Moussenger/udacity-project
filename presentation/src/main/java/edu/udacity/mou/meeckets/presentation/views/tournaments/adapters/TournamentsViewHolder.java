package edu.udacity.mou.meeckets.presentation.views.tournaments.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.presentation.MeecketsViewHolder;
import edu.udacity.mou.meeckets.presentation.R2;
import edu.udacity.mou.meeckets.presentation.utils.DateUtils;
import edu.udacity.mou.meeckets.presentation.utils.DistanceUtils;

/**
 * Created by mou on 11/22/17.
 */

public class TournamentsViewHolder extends MeecketsViewHolder<Tournament> {
    @BindView(R2.id.tournament_background_image)
    ImageView tournamentBackgroundImage;

    @BindView(R2.id.tournament_name_text)
    TextView tournamentNameText;

    @BindView(R2.id.tournament_place_text)
    TextView tournamentPlaceText;

    @BindView(R2.id.tournament_date_text)
    TextView tournamentDateText;

    @BindView(R2.id.tournament_distance_text)
    TextView tournamentDistanceText;

    public TournamentsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Context context, Tournament item) {
        setItem(item);

        Picasso.with(context)
                .load(getItem().getImage())
                .resize(600, 400)
                .centerCrop()
                .into(tournamentBackgroundImage);

        tournamentNameText.setText(getItem().getName());
        tournamentPlaceText.setText(getItem().getLocation().getName());
        tournamentDateText.setText(DateUtils.dateToRelativeTime(context, getItem().getDate()));

        if (getItem().getDistance() != null) {
            tournamentDistanceText.setVisibility(View.VISIBLE);
            tournamentDistanceText.setText(DistanceUtils.toRelative(context, getItem().getDistance()));
        } else {
            tournamentDistanceText.setVisibility(View.GONE);
        }

    }

}
