package edu.udacity.mou.meeckets.domain.interactors.auth;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.device.IMeecketsAccountManager;
import edu.udacity.mou.meeckets.domain.interactors.CompletableUseCase;
import edu.udacity.mou.meeckets.domain.repositories.tournaments.ISubscriptionsRepository;
import io.reactivex.CompletableEmitter;
import io.reactivex.Scheduler;

/**
 * Created by mou on 11/11/17.
 */

public class DoLogout extends CompletableUseCase<Void> {
    private IMeecketsAccountManager accountManager;
    private ISubscriptionsRepository subscriptionsRepository;

    @Inject
    public DoLogout(Scheduler backgroundThread, Scheduler mainThread,
                    ISubscriptionsRepository subscriptionsRepository,
                    IMeecketsAccountManager accountManager) {
        super(backgroundThread, mainThread);
        this.subscriptionsRepository = subscriptionsRepository;
        this.accountManager = accountManager;
    }

    @Override
    protected void onRun(CompletableEmitter emitter, Void parameter) {
        try {
            accountManager.deleteAccount();
            subscriptionsRepository.deleteSubscriptions().subscribe(
                    emitter::onComplete,
                    emitter::onError
            );
        } catch (Exception e) {
            emitter.onError(e);
        }
    }
}
