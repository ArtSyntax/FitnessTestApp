package com.artsyntax.fitnesstest.manager.http;

import com.artsyntax.fitnesstest.dao.StationListDao;
import com.artsyntax.fitnesstest.manager.TestInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ArtSyntax on 21/3/2559.
 */
public interface ApiGetStations {
    //@GET("getstation.php?testcode={testCode}") //base url
    //Call<StationListDao> loadStationsList(@Path("testCode") String testCode);

    @POST("getstation.php?testcode=ABCXYZ") //base url
    Call<StationListDao> loadStationsList();
}
