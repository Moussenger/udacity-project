package edu.udacity.mou.meeckets.data.datasources.database.daos;

import android.arch.persistence.room.Dao;

import edu.udacity.mou.meeckets.data.datasources.database.IBaseDao;
import edu.udacity.mou.meeckets.data.model.entities.AuthEntity;

/**
 * Created by mou on 11/14/17.
 */

@Dao
public interface AuthDao extends IBaseDao<AuthEntity> {

}
