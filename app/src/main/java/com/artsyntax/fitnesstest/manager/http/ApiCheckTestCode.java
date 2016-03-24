package com.artsyntax.fitnesstest.manager.http;


import com.artsyntax.fitnesstest.dao.TestNameDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ArtSyntax on 20/3/2559.
 */
public interface ApiCheckTestCode {
    @GET("checktestcode.php") //base url
    Call<TestNameDao> checkTestCode(@Query("testcode") String surrentTestcode);
}
