package edu.udacity.mou.meeckets.domain.model.tournaments;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mou on 11/22/17.
 */

@Data
@Builder
public class Subscription {
    private long id;
    private String image;
    private String name;
    private Date date;
    private String place;
}
