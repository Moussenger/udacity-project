package edu.udacity.mou.meeckets.data.datasources.database.columns;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.Constraints;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.UniqueConstraint;

import static net.simonvt.schematic.annotation.ConflictResolutionType.REPLACE;
import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;
import static net.simonvt.schematic.annotation.DataType.Type.REAL;
import static net.simonvt.schematic.annotation.DataType.Type.TEXT;

/**
 * Created by mou on 11/14/17.
 */

@Constraints(
        unique = @UniqueConstraint(
                name = "UNIQUE_ID",
                columns = {TournamentColumns.ID},
                onConflict = REPLACE)
)
public interface TournamentColumns {
    @DataType(INTEGER)
    @PrimaryKey
    @AutoIncrement
    String _ID = "_id";

    @DataType(INTEGER)
    String ID = "id";

    @DataType(TEXT)
    String IMAGE = "image";

    @DataType(TEXT)
    String NAME = "name";

    @DataType(INTEGER)
    String DATE = "date";

    @DataType(TEXT)
    String DESCRIPTION = "description";

    @DataType(TEXT)
    String PLACE = "place";

    @DataType(REAL)
    String LATITUDE = "latitude";

    @DataType(REAL)
    String LONGITUDE = "longitude";
}
