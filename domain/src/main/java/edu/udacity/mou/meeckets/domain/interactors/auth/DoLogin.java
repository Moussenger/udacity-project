package edu.udacity.mou.meeckets.domain.interactors.auth;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.device.IMeecketsAccountManager;
import edu.udacity.mou.meeckets.domain.interactors.CompletableUseCase;
import edu.udacity.mou.meeckets.domain.model.auth.Login;
import edu.udacity.mou.meeckets.domain.model.auth.User;
import edu.udacity.mou.meeckets.domain.repositories.auth.IAuthRepository;
import io.reactivex.CompletableEmitter;
import io.reactivex.Scheduler;

/**
 * Created by mou on 11/11/17.
 */

public class DoLogin extends CompletableUseCase<Login> {
    private IAuthRepository authRepository;
    private IMeecketsAccountManager accountManager;

    @Inject
    public DoLogin(Scheduler backgroundThread, Scheduler mainThread,
                   IAuthRepository authRepository, IMeecketsAccountManager accountManager) {
        super(backgroundThread, mainThread);
        this.authRepository = authRepository;
        this.accountManager = accountManager;
    }

    @Override
    protected void onRun(CompletableEmitter emitter, Login parameter) {
        authRepository.login(parameter).subscribe(
                user -> onUser(emitter, user),
                emitter::onError
        );
    }

    private void onUser(CompletableEmitter emitter, User user) {
        try {
            accountManager.saveAccount(user.getAccessToken());
            authRepository.saveUser(user).subscribe(
                    emitter::onComplete,
                    emitter::onError
            );
        } catch (Exception e) {
            emitter.onError(e);
        }
    }
}
