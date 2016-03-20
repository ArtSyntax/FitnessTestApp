package com.artsyntax.fitnesstest.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ArtSyntax on 21/3/2559.
 */
public class StationDao {
    @SerializedName("station_name")         private String stationName;
    @SerializedName("station_unit")         private String stationUnit;

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
}
