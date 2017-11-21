package edu.udacity.mou.meeckets.di.modules.domain;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.meeckets.domain.device.IMeecketsAccountManager;
import edu.udacity.mou.meeckets.domain.interactors.auth.DoLogin;
import edu.udacity.mou.meeckets.domain.repositories.auth.IAuthRepository;
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

}
