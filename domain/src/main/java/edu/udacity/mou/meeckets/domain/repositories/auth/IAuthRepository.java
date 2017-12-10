package edu.udacity.mou.meeckets.domain.repositories.auth;


import edu.udacity.mou.meeckets.domain.model.auth.Login;
import edu.udacity.mou.meeckets.domain.model.auth.User;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mou on 11/9/17.
 */

public interface IAuthRepository {
    Observable<User> login(Login auth);

    Single<User> getUser();

    Completable saveUser(User user);
}
