package com.artsyntax.fitnesstest.utils;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by ArtSyntax on 27/3/2559.
 */
public class Score {

    boolean atServer;
    String id;
    String score;
    String station;
    String date;
    String firstname;
    String lastname;
    String testStationID;
    String testCode;

    public boolean isAtServer() {
        return atServer;
    }

    public void setAtServer(boolean atServer) {
        this.atServer = atServer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTestStationID() {
        return testStationID;
    }

    public void setTestStationID(String testStationID) {
        this.testStationID = testStationID;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }
}
