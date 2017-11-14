package edu.udacity.mou.meeckets.domain.model.auth;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mou on 11/11/17.
 */

@Data
@Builder
public class Login {
    private String username;
    private String password;
}