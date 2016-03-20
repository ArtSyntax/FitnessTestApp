package com.artsyntax.fitnesstest.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ArtSyntax on 21/3/2559.
 */
public class StationListDao {
    @SerializedName("stations")     private List<StationDao> stations;

    public List<StationDao> getStations() {
        return stations;
    }

    public void setStations(List<StationDao> stations) {
        this.stations = stations;
    }
}
