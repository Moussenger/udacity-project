package edu.udacity.mou.meeckets.data.datasources.network.auth;

import javax.inject.Singleton;

import edu.udacity.mou.meeckets.data.NetworkUtils;
import edu.udacity.mou.meeckets.data.model.network.responses.AuthResponse;
import edu.udacity.mou.meeckets.domain.model.auth.Login;

/**
 * Created by mou on 11/13/17.
 */

@Singleton
public class AuthNetworkDatasource implements IAuthNetworkDatasource {
    @Override
    public AuthResponse login(Login parameter) {
        if (!NetworkUtils.fakeRequest(5)) {
            throw new RuntimeException();
        }

        return AuthResponse.builder().refreshToken("3ba35f34ca657c68aacb8e7a7f7cbb78").build();
    }
}
