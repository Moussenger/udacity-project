package edu.udacity.mou.meeckets.domain.interactors.tournaments;

import java.util.List;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.device.IMeecketsLocationProvider;
import edu.udacity.mou.meeckets.domain.device.IMeecketsPermissionChecker;
import edu.udacity.mou.meeckets.domain.interactors.CompletableUseCase;
import edu.udacity.mou.meeckets.domain.model.tournaments.Location;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;
import io.reactivex.CompletableEmitter;
import io.reactivex.Scheduler;

/**
 * Created by mou on 11/11/17.
 */

public class UpdateTournamentsLocation extends CompletableUseCase<List<Tournament>> {
    private IMeecketsLocationProvider locationProvider;
    private IMeecketsPermissionChecker permissionChecker;

    @Inject
    public UpdateTournamentsLocation(Scheduler backgroundThread, Scheduler mainThread,
                                     IMeecketsLocationProvider locationProvider, IMeecketsPermissionChecker permissionChecker) {
        super(backgroundThread, mainThread);
        this.locationProvider = locationProvider;
        this.permissionChecker = permissionChecker;
    }

    @Override
    protected void onRun(CompletableEmitter emitter, List<Tournament> tournaments) {
        if (permissionChecker.hasLocationPermission()) {
            locationProvider.getLocation(location -> this.onLocation(emitter, tournaments, location));
        } else {
            emitter.onComplete();
        }
    }

    private void onLocation(CompletableEmitter emitter, List<Tournament> tournaments, Location userLocation) {
        if (userLocation != null && tournaments != null) {
            for (Tournament tournament : tournaments) {
                tournament.setDistance(locationProvider.getDistance(tournament.getLocation(), userLocation));
            }
        }

        emitter.onComplete();
    }
}
