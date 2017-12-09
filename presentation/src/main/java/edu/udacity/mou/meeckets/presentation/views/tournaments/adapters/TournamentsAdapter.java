package edu.udacity.mou.meeckets.presentation.views.tournaments.adapters;

import android.content.Context;
import android.view.View;

import java.util.Collections;
import java.util.List;

import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.UpdateTournamentsLocation;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import edu.udacity.mou.meeckets.presentation.MeecketsBaseAdapter;
import edu.udacity.mou.meeckets.presentation.R;

/**
 * Created by mou on 11/22/17.
 */
public class TournamentsAdapter extends MeecketsBaseAdapter<TournamentsViewHolder, Tournament> {
    private UpdateTournamentsLocation updateTournamentsLocation;

    public TournamentsAdapter(Context context, StorageMapper<Tournament> mapper, UpdateTournamentsLocation updateTournamentsLocation) {
        super(context, mapper);
        this.updateTournamentsLocation = updateTournamentsLocation;
    }

    @Override
    public void onNewItems(List<Tournament> items) {
        updateTournamentsLocation.run(items).subscribe(() -> onItemsUpdated(items));
    }

    public void onItemsUpdated(List<Tournament> items) {
        Collections.sort(items);
        notifyDataSetChanged();
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
