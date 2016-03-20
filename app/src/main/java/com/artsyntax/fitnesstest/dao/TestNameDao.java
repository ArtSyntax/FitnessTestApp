package com.artsyntax.fitnesstest.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ArtSyntax on 21/3/2559.
 */
public class TestNameDao {
    @SerializedName("FOUND")             private String found;
    @SerializedName("test_name")         private String testName;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }
}
