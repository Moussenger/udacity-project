package edu.udacity.mou.meeckets.data.model.network.responses;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mou on 11/9/17.
 */

@Data
@Builder
public class AuthResponse {
    private String username;
    private String profileImage;
    private String accessToken;
}
