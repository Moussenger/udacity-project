package edu.udacity.mou.meeckets.presentation.views.profile.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.presentation.MeecketsViewHolder;
import edu.udacity.mou.meeckets.presentation.R2;
import edu.udacity.mou.meeckets.presentation.utils.DateUtils;

/**
 * Created by mou on 11/22/17.
 */

public class SubscriptionsViewHolder extends MeecketsViewHolder<Subscription> {
    @BindView(R2.id.subscription_background_image)
    ImageView subscriptionBackgroundImage;

    @BindView(R2.id.subscription_name_text)
    TextView subscriptionNameText;

    @BindView(R2.id.subscription_place_text)
    TextView subscriptionPlaceText;

    @BindView(R2.id.subscription_date_text)
    TextView subscriptionDateText;

    public SubscriptionsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Context context, Subscription item) {
        setItem(item);

        Picasso.with(context)
                .load(getItem().getImage())
                .resize(600, 400)
                .centerCrop()
                .into(subscriptionBackgroundImage);

        subscriptionNameText.setText(getItem().getName());
        subscriptionPlaceText.setText(getItem().getPlace());
        subscriptionDateText.setText(DateUtils.dateToRelativeTime(context, getItem().getDate()));
    }

}
