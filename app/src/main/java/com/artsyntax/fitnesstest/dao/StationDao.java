package com.artsyntax.fitnesstest.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ArtSyntax on 21/3/2559.
 */
public class StationDao {
    @SerializedName("test_station_id")      private String testStationId;
    @SerializedName("station_name")         private String stationName;
    @SerializedName("station_unit")         private String stationUnit;
    @SerializedName("low_score_bound")      private String lowScoreBound;
    @SerializedName("high_score_bound")     private String highScoreBound;

    public String getTestStationId() {
        return testStationId;
    }

    public void setTestStationId(String testStationId) {
        this.testStationId = testStationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationUnit() {
        return stationUnit;
    }

    public void setStationUnit(String stationUnit) {
        this.stationUnit = stationUnit;
    }

    public String getLowScoreBound() {
        return lowScoreBound;
    }

    public void setLowScoreBound(String lowScoreBound) {
        this.lowScoreBound = lowScoreBound;
    }

    public String getHighScoreBound() {
        return highScoreBound;
    }

    public void setHighScoreBound(String highScoreBound) {
        this.highScoreBound = highScoreBound;
    }
}
