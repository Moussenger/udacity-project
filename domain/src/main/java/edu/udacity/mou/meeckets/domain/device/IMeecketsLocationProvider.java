package edu.udacity.mou.meeckets.domain.device;

import edu.udacity.mou.meeckets.domain.model.tournaments.Location;

/**
 * Created by mou on 12/9/17.
 */

public interface IMeecketsLocationProvider {
    void getLocation(IMeecketsLocationProviderListener listener);

    int getDistance(Location from, Location to);

    interface IMeecketsLocationProviderListener {
        void onLocation(Location location);
    }
}
