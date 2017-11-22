package edu.udacity.mou.meeckets.di.modules.domain;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.domain.device.IMeecketsAccountManager;
import edu.udacity.mou.meeckets.domain.interactors.auth.CheckLogin;
import edu.udacity.mou.meeckets.domain.interactors.auth.DoLogin;
import edu.udacity.mou.meeckets.domain.interactors.tournaments.GetTournaments;
import edu.udacity.mou.meeckets.domain.repositories.auth.IAuthRepository;
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
    CheckLogin provideCheckLogin(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                                 IMeecketsAccountManager accountManager) {
        return new CheckLogin(backgroundThread, mainThread, accountManager);
    }

    @Provides
    GetTournaments provideGetTournaments(@Named("background") Scheduler backgroundThread, @Named("main") Scheduler mainThread,
                                         ITournamentsRepository tournamentsRepository) {
        return new GetTournaments(backgroundThread, mainThread, tournamentsRepository);
    }

}
