package edu.udacity.mou.meeckets.data.repositories;


import javax.inject.Inject;
import javax.inject.Singleton;

import edu.udacity.mou.meeckets.data.datasources.database.MeecketsDatabaseDatasource;
import edu.udacity.mou.meeckets.data.datasources.network.auth.IAuthNetworkDatasource;
import edu.udacity.mou.meeckets.data.model.entities.AuthEntity;
import edu.udacity.mou.meeckets.data.model.network.responses.AuthResponse;
import edu.udacity.mou.meeckets.domain.model.auth.Login;
import edu.udacity.mou.meeckets.domain.model.auth.Token;
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
    public Observable<Token> login(final Login auth) {
        return Observable.create(emitter -> this.login(emitter, auth));
    }

    @Override
    public Completable saveToken(final Token token) {
        return Completable.create(emitter -> this.saveToken(emitter, token));
    }

    private void login(ObservableEmitter<Token> emitter, Login auth) {
        try {
            AuthResponse response = authNetworkDatasource.login(auth);
            Token token = Token.builder().refreshToken(response.getRefreshToken()).build();

            emitter.onNext(token);
        } catch (Exception e) {
            emitter.onError(e);
        }
    }

    private void saveToken(CompletableEmitter emitter, Token token) {
        try {
            AuthEntity auth = AuthEntity.builder().token(token.getRefreshToken()).build();
            databaseDatasource.authDao().insert(auth);
            emitter.onComplete();
        } catch (Exception e) {
            emitter.onError(e);
        }
    }
}
