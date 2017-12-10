package edu.udacity.mou.meeckets.presentation.homescreenwidgets;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import edu.udacity.mou.meeckets.data.datasources.database.mappers.StorageMapper;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetSubscriptions;
import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.presentation.R;
import edu.udacity.mou.meeckets.presentation.utils.DateUtils;

/**
 * Created by mou on 12/10/17.
 */

public class SubscriptionsListWidgetService extends RemoteViewsService {
    @Inject
    GetSubscriptions getSubscriptions;

    @Inject
    StorageMapper<Subscription> subscriptionStorageMapper;


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        AndroidInjection.inject(this);
        return new ListRemoteViewsFactory(this.getApplicationContext(), getSubscriptions, subscriptionStorageMapper);
    }

    private class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
        private GetSubscriptions getSubscriptions;
        private StorageMapper<Subscription> subscriptionStorageMapper;
        private Context context;
        private List<Subscription> subscriptions;
        private Uri uri;

        public ListRemoteViewsFactory(Context context, GetSubscriptions getSubscriptions,
                                      StorageMapper<Subscription> subscriptionStorageMapper) {
            this.context = context;
            this.getSubscriptions = getSubscriptions;
            this.subscriptionStorageMapper = subscriptionStorageMapper;
        }

        @Override
        public void onCreate() {
            getSubscriptions.run(null).subscribe((uri) -> {
                this.uri = Uri.parse(uri);
            });
        }

        @Override
        public void onDataSetChanged() {
            if (uri != null) {
                Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
                this.subscriptions = subscriptionStorageMapper.all(cursor);
                cursor.close();
            }
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return subscriptions == null ? 0 : subscriptions.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            if (subscriptions != null && subscriptions.size() > 0) {
                Subscription subscription = subscriptions.get(position);

                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.item_widget_subscription);
                views.setTextViewText(R.id.widget_subscription_name_text, subscription.getName());
                views.setTextViewText(R.id.widget_subscription_place_text, subscription.getPlace());
                views.setTextViewText(R.id.widget_subscription_date_text, DateUtils.dateToRelativeTime(context, subscription.getDate()));

                return views;
            }

            return null;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
