package edu.udacity.mou.meeckets.domain.interactors.auth;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.device.IMeecketsAccountManager;
import edu.udacity.mou.meeckets.domain.interactors.SingleUseCase;
import io.reactivex.Scheduler;
import io.reactivex.SingleEmitter;

/**
 * Created by mou on 11/22/17.
 */

public class CheckLogin extends SingleUseCase<Boolean, Void> {
    private IMeecketsAccountManager accountManager;

    @Inject
    public CheckLogin(Scheduler backgroundThread, Scheduler mainThread,
                      IMeecketsAccountManager accountManager) {
        super(backgroundThread, mainThread);
        this.accountManager = accountManager;
    }

    @Override
    protected void onRun(SingleEmitter<Boolean> emitter, Void parameter) {
        emitter.onSuccess(accountManager.isLogged());
    }
}
