package edu.udacity.mou.meeckets.presentation.views.profile.adapters;

import android.content.Context;
import android.view.View;

import java.util.List;

import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.presentation.MeecketsBaseAdapter;
import edu.udacity.mou.meeckets.presentation.R;

/**
 * Created by mou on 11/22/17.
 */
public class SubscriptionsAdapter extends MeecketsBaseAdapter<SubscriptionsViewHolder, Subscription> {
    public SubscriptionsAdapter(Context context, StorageMapper<Subscription> mapper) {
        super(context, mapper);
    }

    @Override
    public void onNewItems(List<Subscription> items) {
        // Nothing to do here
    }

    @Override
    public int getLayout() {
        return R.layout.item_subscription;
    }

    @Override
    public SubscriptionsViewHolder createViewHolder(View item) {
        return new SubscriptionsViewHolder(item);
    }
}
