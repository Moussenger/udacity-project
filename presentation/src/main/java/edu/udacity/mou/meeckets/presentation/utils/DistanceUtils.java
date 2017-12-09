package edu.udacity.mou.meeckets.presentation.utils;


import android.content.Context;

import edu.udacity.mou.meeckets.presentation.R;

/**
 * Created by mou on 12/8/17.
 */

public class DistanceUtils {

    private static final int KM_THRESHOLD = 1000;

    public static String toRelative(Context context, int distance) {
        if (distance < KM_THRESHOLD) {
            return context.getResources().getString(R.string.distance_meters, distance);
        } else {
            int distanceInKM = Math.round((float) distance / KM_THRESHOLD);
            return context.getResources().getString(R.string.distance_kilometers, distanceInKM);
        }
    }
}
