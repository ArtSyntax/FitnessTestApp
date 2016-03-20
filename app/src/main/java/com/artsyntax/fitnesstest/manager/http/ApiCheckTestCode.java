package com.artsyntax.fitnesstest.manager.http;


import com.artsyntax.fitnesstest.dao.TestNameDao;
import com.artsyntax.fitnesstest.manager.TestInfo;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by ArtSyntax on 20/3/2559.
 */
public interface ApiCheckTestCode {
    TestInfo testInfo = null;
    @POST("checktestcode.php?testcode=ABCXYZ") //base url
    Call<TestNameDao> checkTestCode();
}
