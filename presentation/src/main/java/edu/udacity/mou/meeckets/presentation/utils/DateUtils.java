package edu.udacity.mou.meeckets.presentation.utils;


import android.content.Context;

import java.text.DateFormat;
import java.util.Date;

import edu.udacity.mou.meeckets.presentation.R;

/**
 * Created by mou on 12/8/17.
 */

public class DateUtils {

    public static String parseDate(Date date) {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(date);
    }

    public static String dateToRelativeTime(Context context, Date date) {
        long dateTime = date.getTime();
        long current = new Date().getTime();
        long diff = (dateTime - current) / 1000;

        if (diff < 60) {
            return seconds(context);
        }

        diff /= 60;

        if (diff < 60) {
            return minutes(context, diff);
        }

        diff /= 60;

        if (diff < 24) {
            return hours(context, diff);
        }

        diff /= 24;

        if (diff < 7) {
            return days(context, diff);
        }

        diff /= 7;

        if (diff < 4) {
            return weeks(context, diff);
        }

        diff /= 4;

        return months(context, diff);
    }

    private static String seconds(Context context) {
        return context.getResources().getString(R.string.date_relative_less_minute);
    }

    private static String minutes(Context context, long time) {
        return toRelative(context, time, R.plurals.date_relative_minutes);
    }

    private static String hours(Context context, long time) {
        return toRelative(context, time, R.plurals.date_relative_hours);
    }

    private static String days(Context context, long time) {
        return toRelative(context, time, R.plurals.date_relative_days);
    }

    private static String weeks(Context context, long time) {
        return toRelative(context, time, R.plurals.date_relative_weeks);
    }

    private static String months(Context context, long time) {
        return toRelative(context, time, R.plurals.date_relative_months);
    }

    private static String toRelative(Context context, long time, int pluralResource) {
        return String.format(context.getResources().getQuantityString(pluralResource, (int) time), time);
    }

}
