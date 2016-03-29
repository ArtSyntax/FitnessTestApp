package com.artsyntax.fitnesstest.manager.http;

import com.artsyntax.fitnesstest.dao.ResultDao;
import com.artsyntax.fitnesstest.dao.StationListDao;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ArtSyntax on 21/3/2559.
 */
public interface ApiSetScore {

    @GET("setscore.php") //base url
    Call<ResultDao> submit(@Query("testcode") String surrentTestcode,
                           @Query("teststationid") String currentTeststationid,
                           @Query("usertag") String currentUsertag,
                           @Query("score") String currentScore);

//
//    @GET("setscore.php") //base url
//    Call<ScoreDao> submitScore(@QueryMap Map("testcode", "teststationid", "usertag", "score")
//    String currentTestcode, String currentTeststationid, String currentUsertag, String currentScore);

    //setscore.php?testcode=ABCXYZ&teststationid=28&usertag=150&score=15
}
