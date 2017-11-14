package edu.udacity.mou.meeckets.domain.interactors.auth;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.CompletableUseCase;
import edu.udacity.mou.meeckets.domain.model.auth.Login;
import edu.udacity.mou.meeckets.domain.model.auth.Token;
import edu.udacity.mou.meeckets.domain.repositories.auth.IAuthRepository;
import io.reactivex.CompletableEmitter;
import io.reactivex.Scheduler;

/**
 * Created by mou on 11/11/17.
 */

public class DoLogin extends CompletableUseCase<Login> {
    private IAuthRepository authRepository;

    @Inject
    public DoLogin(Scheduler backgroundThread, Scheduler mainThread,
                   IAuthRepository authRepository) {
        super(backgroundThread, mainThread);
        this.authRepository = authRepository;
    }

    @Override
    protected void onRun(CompletableEmitter emitter, Login parameter) {
        authRepository.login(parameter).subscribe(
                token -> onToken(emitter, token),
                emitter::onError
        );
    }

    private void onToken(CompletableEmitter emitter, Token token) {
        System.out.println("TOKEN " + token.getRefreshToken());

        authRepository.saveToken(token).subscribe(
                emitter::onComplete,
                emitter::onError
        );
    }
}
