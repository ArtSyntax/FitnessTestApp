package com.artsyntax.fitnesstest.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ArtSyntax on 21/3/2559.
 */
public class ResultDao {
    @SerializedName("FOUND")            private String found;
    @SerializedName("user_id")          private String userId;
    @SerializedName("firstname")        private String firstname;
    @SerializedName("lastname")         private String lastname;

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
