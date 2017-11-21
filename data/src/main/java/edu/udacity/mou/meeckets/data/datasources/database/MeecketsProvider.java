package edu.udacity.mou.meeckets.data.datasources.database;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by mou on 11/14/17.
 */

@ContentProvider(authority = MeecketsProvider.AUTHORITY, database = MeecketsDatabase.class)
public class MeecketsProvider {
    public static final String AUTHORITY = "edu.udacity.mou.meeckets.data";

    private static final String DIR_TYPE = "vnd.android.cursor.dir/";
    private static final String ITEM_TYPE = "vnd.android.cursor.item/";

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path {
        String USERS = "users";
    }

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();

        for (String path : paths) {
            builder.appendPath(path);
        }

        return builder.build();
    }

    @TableEndpoint(table = MeecketsDatabase.USERS)
    public static class Users {
        @ContentUri(path = Path.USERS, type = DIR_TYPE + "user", limit = "1")
        public static Uri CONTENT_URI = buildUri(Path.USERS);
    }
}
