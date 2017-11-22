package edu.udacity.mou.meeckets.presentation.views.tournaments.adapters;

import android.content.Context;
import android.view.View;

import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.presentation.MeecketsBaseAdapter;
import edu.udacity.mou.meeckets.presentation.R;

/**
 * Created by mou on 11/22/17.
 */

public class TournamentsAdapter extends MeecketsBaseAdapter<TournamentsViewHolder, Tournament> {

    public TournamentsAdapter(Context context, StorageMapper<Tournament> mapper) {
        super(context, mapper);
    }

    @Override
    public int getLayout() {
        return R.layout.item_tournament;
    }

    @Override
    public TournamentsViewHolder createViewHolder(View item) {
        return new TournamentsViewHolder(item, getMapper());
    }
}
