package edu.udacity.mou.meeckets.data.model.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mou on 11/14/17.
 */
@Entity(tableName = "auth")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthEntity {

    @PrimaryKey
    @NonNull
    private String token;
}
