package edu.udacity.mou.meeckets.presentation.views.tournaments.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.presentation.MeecketsViewHolder;
import edu.udacity.mou.meeckets.presentation.R2;

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

    public TournamentsViewHolder(View itemView, StorageMapper<Tournament> mapper) {
        super(itemView, mapper);
    }

    @Override
    public void bind(Context context, Cursor cursor) {
        Tournament tournament = getMapper().single(cursor);

        Picasso.with(context)
                .load(tournament.getImage())
                .resize(600, 400)
                .centerCrop()
                .into(tournamentBackgroundImage);

        tournamentNameText.setText(tournament.getName());
        tournamentPlaceText.setText(tournament.getLocation().getName());
    }

}
