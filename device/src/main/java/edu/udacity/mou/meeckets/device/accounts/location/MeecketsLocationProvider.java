package edu.udacity.mou.meeckets.device.accounts.location;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.udacity.mou.meeckets.domain.device.IMeecketsLocationProvider;

/**
 * Created by mou on 12/9/17.
 */

@Singleton
public class MeecketsLocationProvider implements IMeecketsLocationProvider {
    private FusedLocationProviderClient fusedLocationClient;

    @Inject
    public MeecketsLocationProvider(Context context) {
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void getLocation(IMeecketsLocationProviderListener listener) {
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> this.onLocation(location, listener));
    }

    @Override
    public int getDistance(edu.udacity.mou.meeckets.domain.model.tournaments.Location from, edu.udacity.mou.meeckets.domain.model.tournaments.Location to) {
        Location fromLocation = createFromDomain(from);
        Location toLocation = createFromDomain(to);

        return Math.round(fromLocation.distanceTo(toLocation));
    }

    private void onLocation(Location location, IMeecketsLocationProviderListener listener) {
        if (location == null) {
            listener.onLocation(null);
        } else {
            edu.udacity.mou.meeckets.domain.model.tournaments.Location domainLocation = edu.udacity.mou.meeckets.domain.model.tournaments.Location.builder()
                    .latitude((float) location.getLatitude())
                    .longitude((float) location.getLongitude())
                    .build();

            listener.onLocation(domainLocation);
        }
    }

    private Location createFromDomain(edu.udacity.mou.meeckets.domain.model.tournaments.Location domainLocation) {
        Location fromLocation = new Location(domainLocation.getName());

        fromLocation.setLatitude(domainLocation.getLatitude());
        fromLocation.setLongitude(domainLocation.getLongitude());

        return fromLocation;
    }
}
