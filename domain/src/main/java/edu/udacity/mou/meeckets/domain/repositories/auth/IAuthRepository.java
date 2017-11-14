package edu.udacity.mou.meeckets.domain.repositories.auth;


import edu.udacity.mou.meeckets.domain.model.auth.Login;
import edu.udacity.mou.meeckets.domain.model.auth.Token;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by mou on 11/9/17.
 */

public interface IAuthRepository {
    Observable<Token> login(Login auth);

    Completable saveToken(Token token);
}
