package edu.udacity.mou.meeckets.data.datasources.network.auth;

import java.util.UUID;

import javax.inject.Singleton;

import edu.udacity.mou.meeckets.data.NetworkUtils;
import edu.udacity.mou.meeckets.data.model.network.responses.AuthResponse;
import edu.udacity.mou.meeckets.domain.exceptions.InternalServerErrorException;
import edu.udacity.mou.meeckets.domain.exceptions.InvalidCredentialsException;
import edu.udacity.mou.meeckets.domain.exceptions.ServerException;
import edu.udacity.mou.meeckets.domain.model.auth.Login;

/**
 * Created by mou on 11/13/17.
 */

@Singleton
public class MockAuthNetworkDatasource implements IAuthNetworkDatasource {

    private static final int LOGIN_FAIL_RATE = 5;

    private static final String USERNAME = "winner";
    private static final String PASSWORD = "thebest";

    @Override
    public AuthResponse login(Login login) throws ServerException {
        if (!NetworkUtils.fakeRequest(LOGIN_FAIL_RATE)) {
            throw new InternalServerErrorException();
        }

        if (!USERNAME.equals(login.getUsername()) || !PASSWORD.equals(login.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return AuthResponse.builder().refreshToken(UUID.randomUUID().toString()).build();
    }
}
