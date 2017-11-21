package edu.udacity.mou.meeckets.data.datasources.network.auth;

import edu.udacity.mou.meeckets.data.model.network.responses.AuthResponse;
import edu.udacity.mou.meeckets.domain.exceptions.server.ServerException;
import edu.udacity.mou.meeckets.domain.model.auth.Login;

/**
 * Created by mou on 11/9/17.
 */

public interface IAuthNetworkDatasource {
    AuthResponse login(Login parameter) throws ServerException;
}
