package edu.udacity.mou.meeckets.di.modules.domain;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.domain.device.IMeecketsAccountManager;
import edu.udacity.mou.meeckets.domain.device.IMeecketsLocationProvider;
import edu.udacity.mou.meeckets.domain.device.IMeecketsPermissionChecker;
import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;
import edu.udacity.mou.meeckets.domain.interactors.auth.DoLogin;
import edu.udacity.mou.meeckets.domain.interactors.auth.DoLogout;
import edu.udacity.mou.meeckets.domain.interactors.auth.GetUser;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.AddSubscription;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.DeleteSubscription;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.DeleteSubscriptions;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetSubscription;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetSubscriptions;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetTournaments;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.UpdateTournamentsLocation;
import edu.udacity.mou.meeckets.domain.repositories.auth.IAuthRepository;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ISubscriptionsRepository;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ITournamentsRepository;
import io.reactivex.Scheduler;

/**
 * Created by mou on 11/11/17.
 */

@Module
public class InteractorsModule {
    @Provides
    DoLogin provideDoLogin(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                           IAuthRepository authRepository, IMeecketsAccountManager accountManager) {
        return new DoLogin(backgroundThread, mainThread, authRepository, accountManager);
    }

    @Provides
    DoLogout provideDoLogout(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                             ISubscriptionsRepository subscriptionsRepository, IMeecketsAccountManager accountManager) {
        return new DoLogout(backgroundThread, mainThread, subscriptionsRepository, accountManager);
    }

    @Provides
    GetUser provideGetUser(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                           IAuthRepository authRepository) {
        return new GetUser(backgroundThread, mainThread, authRepository);
    }

    @Provides
    CheckLogin provideCheckLogin(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                                 IMeecketsAccountManager accountManager) {
        return new CheckLogin(backgroundThread, mainThread, accountManager);
    }

    @Provides
    GetTournaments provideGetTournaments(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                                         ITournamentsRepository tournamentsRepository) {
        return new GetTournaments(backgroundThread, mainThread, tournamentsRepository);
    }

    @Provides
    UpdateTournamentsLocation provideUpdateTournamentsLocation(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                                                               IMeecketsPermissionChecker permissionChecker, IMeecketsLocationProvider locationProvider) {
        return new UpdateTournamentsLocation(backgroundThread, mainThread, locationProvider, permissionChecker);
    }

    @Provides
    AddSubscription provideAddSubscription(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                                           ISubscriptionsRepository subscriptionsRepository) {
        return new AddSubscription(backgroundThread, mainThread, subscriptionsRepository);
    }

    @Provides
    DeleteSubscription provideDeleteSubscription(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                                                 ISubscriptionsRepository subscriptionsRepository) {
        return new DeleteSubscription(backgroundThread, mainThread, subscriptionsRepository);
    }

    @Provides
    DeleteSubscriptions provideDeleteSubscriptions(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                                                   ISubscriptionsRepository subscriptionsRepository) {
        return new DeleteSubscriptions(backgroundThread, mainThread, subscriptionsRepository);
    }

    @Provides
    GetSubscription provideGetSubscription(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                                           ISubscriptionsRepository subscriptionsRepository) {
        return new GetSubscription(backgroundThread, mainThread, subscriptionsRepository);
    }

    @Provides
    GetSubscriptions provideGetSubscriptions(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                                             ISubscriptionsRepository subscriptionsRepository) {
        return new GetSubscriptions(backgroundThread, mainThread, subscriptionsRepository);
    }

}
