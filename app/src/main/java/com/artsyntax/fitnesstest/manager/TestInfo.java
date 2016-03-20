package com.artsyntax.fitnesstest.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;


public class TestInfo {

    private static TestInfo instance;
    private static String serverIp;
    private static String testCode;
    private static String testName;
    private static String currentStation;
    private static int userTagId;
    private static float userScore;

    public static TestInfo getInstance() {
        if (instance == null)
            instance = new TestInfo();
        return instance;
    }

    private Context mContext;

    private TestInfo() {
        mContext = Contextor.getInstance().getContext();
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

    public static String getCurrentStation() {
        return currentStation;
    }

    public static void setCurrentStation(String currentStation) {
        TestInfo.currentStation = currentStation;
    }

    public static int getUserTagId() {
        return userTagId;
    }

    public static void setUserTagId(int userTagId) {
        TestInfo.userTagId = userTagId;
    }

    public static float getUserScore() {
        return userScore;
    }

    public static void setUserScore(float userScore) {
        TestInfo.userScore = userScore;
    }
}
