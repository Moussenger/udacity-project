package edu.udacity.mou.meeckets.domain.model.tournaments;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mou on 11/22/17.
 */

@Data
@Builder
public class Location implements Serializable {
    private String name;
    private float latitude;
    private float longitude;
}
