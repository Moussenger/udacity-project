package edu.udacity.mou.meeckets.domain.model.tournaments;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mou on 11/22/17.
 */

@Data
@Builder
public class Tournament implements Serializable, Comparable<Tournament> {
    private long id;
    private String image;
    private String name;
    private Date date;
    private Location location;
    private String description;
    private Integer distance;

    @Override
    public int compareTo(Tournament other) {
        int distanceCompared = compareDistance(other);
        int dateCompared = compareDate(other);
        int nameCompared = compareName(other);

        if (distanceCompared == 0) {
            if (dateCompared == 0) {
                return nameCompared;
            } else {
                return dateCompared;
            }
        } else {
            return distanceCompared;
        }
    }

    private int compareDistance(Tournament other) {
        if (getDistance() == null && other.getDistance() == null) {
            return 0;
        }

        if (getDistance() == null) {
            return -1;
        }

        if (other.getDistance() == null) {
            return 1;
        }

        return getDistance() - other.getDistance();
    }

    private int compareDate(Tournament other) {
        return getDate().compareTo(other.getDate());
    }

    private int compareName(Tournament other) {
        return getName().compareTo(other.getName());
    }
}
