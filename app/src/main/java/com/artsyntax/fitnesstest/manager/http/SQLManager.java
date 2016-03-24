package com.artsyntax.fitnesstest.manager.http;

import android.content.Context;
import android.util.Log;

import com.artsyntax.fitnesstest.manager.TestInfo;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SQLManager {

    private static SQLManager instance;
    private ApiCheckTestCode checkTestCode;
    private ApiGetStations stations;
    TestInfo testInfo;

    public void setServerIP() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+testInfo.getServerIp()+"/healthtest/sql/")
                .addConverterFactory(GsonConverterFactory.create())     // JSON to DAO
                .build();
        checkTestCode = retrofit.create(ApiCheckTestCode.class);
        stations = retrofit.create(ApiGetStations.class);
    }

    public static SQLManager getInstance() {
        if (instance == null)
            instance = new SQLManager();
        else
            instance.setServerIP();
        return instance;
    }

    private Context mContext;

    private SQLManager() {
        mContext = Contextor.getInstance().getContext();
        Log.d("server",testInfo.getServerIp());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+testInfo.getServerIp()+"/healthtest/sql/")
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
