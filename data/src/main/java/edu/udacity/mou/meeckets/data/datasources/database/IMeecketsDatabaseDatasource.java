package edu.udacity.mou.meeckets.data.datasources.database;

import edu.udacity.mou.meeckets.domain.exceptions.database.InsertException;
import edu.udacity.mou.meeckets.domain.model.auth.User;

/**
 * Created by mou on 11/14/17.
 */

public interface IMeecketsDatabaseDatasource {
    void addUser(User user) throws InsertException;

    void deleteUser();
}
