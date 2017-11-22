package edu.udacity.mou.meeckets.data.datasources.network.tournaments;

import java.util.List;

import edu.udacity.mou.meeckets.domain.exceptions.server.ServerException;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;

/**
 * Created by mou on 11/9/17.
 */

public interface ITournamentsNetworkDatasource {
    List<Tournament> tournaments() throws ServerException;
}
