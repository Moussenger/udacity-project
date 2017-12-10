package edu.udacity.mou.meeckets.presentation.homescreenwidgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import edu.udacity.mou.meeckets.presentation.R;

/**
 * Created by mou on 12/10/17.
 */

public class SubscriptionsWidget extends AppWidgetProvider {
    public static final String WIDGET_IDS_KEY = "WidgetIdSKey";

    public static void updateWidgets(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, SubscriptionsWidget.class));

        Intent updateIntent = new Intent();
        updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        updateIntent.putExtra(SubscriptionsWidget.WIDGET_IDS_KEY, widgetIds);
        context.sendBroadcast(updateIntent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra(WIDGET_IDS_KEY)) {
            this.onUpdate(context, AppWidgetManager.getInstance(context), intent.getExtras().getIntArray(WIDGET_IDS_KEY));
        } else {
            super.onReceive(context, intent);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_subscriptions);

        Intent intent = new Intent(context, SubscriptionsListWidgetService.class);
        views.setRemoteAdapter(R.id.widget_subscriptions_list, intent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}
