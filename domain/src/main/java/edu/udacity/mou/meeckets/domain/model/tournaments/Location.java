package edu.udacity.mou.meeckets.domain.model.tournaments;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mou on 11/22/17.
 */

@Data
@Builder
public class Location {
    private String name;
    private float latitude;
    private float longitude;
}
