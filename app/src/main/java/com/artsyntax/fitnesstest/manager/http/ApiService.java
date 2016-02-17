package com.artsyntax.fitnesstest.manager.http;

import com.artsyntax.fitnesstest.dao.PhotoItemCollectionDao;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by ArtSyntax on 18/2/2559.
 */
public interface ApiService {
    @POST("list") //base url
    Call<PhotoItemCollectionDao> loadPhotoList();
}
