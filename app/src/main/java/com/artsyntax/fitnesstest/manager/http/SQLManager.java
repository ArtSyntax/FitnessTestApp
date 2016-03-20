package com.artsyntax.fitnesstest.manager.http;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SQLManager {

    private static SQLManager instance;
    private ApiCheckTestCode checkTestCode;
    private ApiGetStations stations;

    public static SQLManager getInstance() {
        if (instance == null)
            instance = new SQLManager();
        return instance;
    }

    private Context mContext;

    private SQLManager() {

        mContext = Contextor.getInstance().getContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://158.108.34.49/healthtest/sql/")
                .addConverterFactory(GsonConverterFactory.create())     // JSON to DAO
                .build();
        checkTestCode = retrofit.create(ApiCheckTestCode.class);
        stations = retrofit.create(ApiGetStations.class);
    }

    public ApiCheckTestCode getCheckTestCode(){
        return checkTestCode;
    }

    public ApiGetStations getStations(){
        return stations;
    }
}
