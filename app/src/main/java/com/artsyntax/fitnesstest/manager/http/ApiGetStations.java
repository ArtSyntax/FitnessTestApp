package com.artsyntax.fitnesstest.manager.http;

import com.artsyntax.fitnesstest.dao.StationListDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ArtSyntax on 21/3/2559.
 */
public interface ApiGetStations {

    @GET("getstation.php") //base url
    Call<StationListDao> loadStationsList(@Query("testcode") String surrentTestcode);
}
