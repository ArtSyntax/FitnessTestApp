package com.artsyntax.fitnesstest.manager.http;

import com.artsyntax.fitnesstest.dao.StationListDao;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by ArtSyntax on 21/3/2559.
 */
public interface ApiGetStations {
    @POST("getstation.php?testcode=ABCXYZ") //base url
    Call<StationListDao> loadStationsList();
}
