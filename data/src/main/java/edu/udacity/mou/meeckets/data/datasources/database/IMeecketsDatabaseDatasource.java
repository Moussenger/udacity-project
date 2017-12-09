package edu.udacity.mou.meeckets.data.datasources.database;

import android.net.Uri;

import java.util.List;

import edu.udacity.mou.meeckets.domain.exceptions.database.InsertException;
import edu.udacity.mou.meeckets.domain.model.auth.User;
import edu.udacity.mou.meeckets.domain.model.tournaments.Subscription;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;

/**
 * Created by mou on 11/14/17.
 */

public interface IMeecketsDatabaseDatasource {
    void addUser(User user) throws InsertException;

    void deleteUser();

    Uri getTournaments();

    void saveTournaments(List<Tournament> tournaments);

    void addSubscription(Subscription subscription);

    Uri getSubscriptions();

    Subscription getSubscription(long id);

    void deleteSubscription(long id);

    void deleteSubscriptions();

}
