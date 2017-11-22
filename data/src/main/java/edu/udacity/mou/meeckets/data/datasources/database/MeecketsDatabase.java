package edu.udacity.mou.meeckets.data.datasources.database;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

import edu.udacity.mou.meeckets.data.datasources.database.columns.TournamentColumns;
import edu.udacity.mou.meeckets.data.datasources.database.columns.UserColumns;

import static edu.udacity.mou.meeckets.data.datasources.database.MeecketsDatabase.VERSION;

/**
 * Created by mou on 11/14/17.
 */

@Database(version = VERSION)
public abstract class MeecketsDatabase {
    public static final int VERSION = 1;

    @Table(UserColumns.class)
    public static final String USERS = "users";

    @Table(TournamentColumns.class)
    public static final String TOURNAMENTS = "tournaments";
}
