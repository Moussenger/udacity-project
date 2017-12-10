package edu.udacity.mou.meeckets.domain.interactors.auth;

import javax.inject.Inject;

import edu.udacity.mou.meeckets.domain.interactors.SingleUseCase;
import edu.udacity.mou.meeckets.domain.model.auth.User;
import edu.udacity.mou.meeckets.domain.repositories.auth.IAuthRepository;
import io.reactivex.Scheduler;
import io.reactivex.SingleEmitter;

/**
 * Created by mou on 11/11/17.
 */

public class GetUser extends SingleUseCase<User, Void> {
    private IAuthRepository authRepository;

    @Inject
    public GetUser(Scheduler backgroundThread, Scheduler mainThread,
                   IAuthRepository authRepository) {
        super(backgroundThread, mainThread);
        this.authRepository = authRepository;
    }

    @Override
    protected void onRun(SingleEmitter<User> emitter, Void parameter) {
        authRepository.getUser().subscribe(
                emitter::onSuccess,
                emitter::onError
        );
    }
}
