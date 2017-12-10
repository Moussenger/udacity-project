package edu.udacity.mou.meeckets.data.repositories;


import javax.inject.Inject;
import javax.inject.Singleton;

import edu.udacity.mou.meeckets.data.datasources.database.MeecketsDatabaseDatasource;
import edu.udacity.mou.meeckets.data.datasources.network.auth.IAuthNetworkDatasource;
import edu.udacity.mou.meeckets.data.model.network.responses.AuthResponse;
import edu.udacity.mou.meeckets.domain.exceptions.database.InsertException;
import edu.udacity.mou.meeckets.domain.model.auth.Login;
import edu.udacity.mou.meeckets.domain.model.auth.User;
import edu.udacity.mou.meeckets.domain.repositories.auth.IAuthRepository;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;

/**
 * Created by mou on 11/9/17.
 */

@Singleton
public class AuthRepository implements IAuthRepository {
    private IAuthNetworkDatasource authNetworkDatasource;
    private MeecketsDatabaseDatasource databaseDatasource;

    @Inject
    public AuthRepository(IAuthNetworkDatasource authNetworkDatasource,
                          MeecketsDatabaseDatasource databaseDatasource) {
        this.authNetworkDatasource = authNetworkDatasource;
        this.databaseDatasource = databaseDatasource;
    }

    @Override
    public Observable<User> login(final Login auth) {
        return Observable.create(emitter -> this.login(emitter, auth));
    }

    @Override
    public Single<User> getUser() {
        return Single.create(this::retrieveUser);
    }

    @Override
    public Completable saveUser(final User user) {
        return Completable.create(emitter -> this.saveUser(emitter, user));
    }

    private void login(ObservableEmitter<User> emitter, Login auth) {
        try {
            AuthResponse response = authNetworkDatasource.login(auth);
            User user = User.builder()
                    .username(response.getUsername())
                    .profileImage(response.getProfileImage())
                    .accessToken(response.getAccessToken())
                    .build();

            emitter.onNext(user);
        } catch (Exception e) {
            emitter.onError(e);
        }
    }

    private void saveUser(CompletableEmitter emitter, User user) {
        try {
            databaseDatasource.deleteUser();
            databaseDatasource.addUser(user);
            emitter.onComplete();
        } catch (InsertException e) {
            emitter.onError(e);
        }
    }

    private void retrieveUser(SingleEmitter<User> emitter) {
        try {
            emitter.onSuccess(databaseDatasource.getUser());
        } catch (Exception e) {
            emitter.onError(e);
        }
    }
}
