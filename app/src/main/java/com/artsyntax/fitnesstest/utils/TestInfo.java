package com.artsyntax.fitnesstest.utils;

import android.content.Context;
import android.util.Log;

import com.artsyntax.fitnesstest.R;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;


public class TestInfo {

    private static TestInfo instance;
    private static String serverIp;
    private static String testCode;
    private static String testName;
    private static String currentStationName;
    private static String currentStationUnit;
    private static String currentTestStationID;
    private static String userTagId;
    private static float userScore;
    private static float lowScoreBound;
    private static float highScoreBound;

    public static TestInfo getInstance() {
        if (instance == null)
            instance = new TestInfo();
        return instance;
    }

    private Context mContext;

    private TestInfo() {
        mContext = Contextor.getInstance().getContext();
    }

    public static void clear(){
        setServerIp(Contextor.getInstance().getContext().getResources().getString(R.string.default_ip));
        setTestCode(null);
        setTestName(null);
        setCurrentStationName(null);
        setCurrentStationUnit(null);
        setCurrentTestStationID(null);
        setUserTagId(null);
        setUserScore(0);
        setLowScoreBound(0);
        setHighScoreBound(0);
    }

    public static void logData(){

        Log.d("testinfo", "=============== data information ===============");
        Log.d("testinfo", "IP: "+getServerIp());
        Log.d("testinfo", "Test code: "+getTestCode());
        Log.d("testinfo", "Test name: "+getTestName());
        Log.d("testinfo", "Station name: "+getCurrentStationName());
        Log.d("testinfo", "Station unit: "+getCurrentStationUnit());
        Log.d("testinfo", "Test Station ID: " + getCurrentTestStationID());
        Log.d("testinfo", "Low Score Bound: "+getLowScoreBound());
        Log.d("testinfo", "High Score Bound: "+getHighScoreBound());
        Log.d("testinfo", "User Tag Id: "+getUserTagId());
        Log.d("testinfo", "User Score: "+getUserScore());
    }

    public static String getServerIp() {
        return serverIp;
    }

    public static void setServerIp(String serverIp) {
        TestInfo.serverIp = serverIp;
    }

    public static String getTestCode() {
        return testCode;
    }

    public static void setTestCode(String testCode) {
        TestInfo.testCode = testCode;
    }

    public static String getTestName() {
        return testName;
    }

    public static void setTestName(String testName) {
        TestInfo.testName = testName;
    }

    public static String getCurrentStationName() {
        return currentStationName;
    }

    public static void setCurrentStationName(String currentStationName) {
        TestInfo.currentStationName = currentStationName;
    }

    public static String getCurrentStationUnit() {
        return currentStationUnit;
    }

    public static void setCurrentStationUnit(String currentStationUnit) {
        TestInfo.currentStationUnit = currentStationUnit;
    }

    public static String getCurrentTestStationID() {
        return currentTestStationID;
    }

    public static void setCurrentTestStationID(String currentTestStationID) {
        TestInfo.currentTestStationID = currentTestStationID;
    }

    public static float getHighScoreBound() {
        return highScoreBound;
    }

    public static void setHighScoreBound(float highScoreBound) {
        TestInfo.highScoreBound = highScoreBound;
    }

    public static float getLowScoreBound() {
        return lowScoreBound;
    }

    public static void setLowScoreBound(float lowScoreBound) {
        TestInfo.lowScoreBound = lowScoreBound;
    }

    public static String getUserTagId() {
        return userTagId;
    }

    public static void setUserTagId(String userTagId) {
        TestInfo.userTagId = userTagId;
    }

    public static float getUserScore() {
        return userScore;
    }

    public static void setUserScore(float userScore) {
        TestInfo.userScore = userScore;
    }
}
