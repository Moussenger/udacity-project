package edu.udacity.mou.meeckets.data.repositories;


import javax.inject.Inject;
import javax.inject.Singleton;

import edu.udacity.mou.meeckets.data.datasources.database.MeecketsDatabaseDatasource;
import edu.udacity.mou.meeckets.data.datasources.network.auth.IAuthNetworkDatasource;
import edu.udacity.mou.meeckets.data.model.entities.AuthEntity;
import edu.udacity.mou.meeckets.data.model.network.responses.AuthResponse;
import edu.udacity.mou.meeckets.domain.model.auth.Login;
import edu.udacity.mou.meeckets.domain.model.auth.User;
import edu.udacity.mou.meeckets.domain.repositories.auth.IAuthRepository;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

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
            AuthEntity auth = AuthEntity.builder().token(user.getAccessToken()).build();
            databaseDatasource.authDao().insert(auth);
            emitter.onComplete();
        } catch (Exception e) {
            emitter.onError(e);
        }
    }
}
