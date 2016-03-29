package com.artsyntax.fitnesstest.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArtSyntax on 27/3/2559.
 */
public class ScoreList {

    private  List<Score> allScore;

    public List<Score> getInstance() {
        if (allScore == null)
            allScore = new ArrayList<>();
        return allScore;
    }

    public List<Score> getAllScore() {
        return allScore;
    }

    public void setAllScore(List<Score> allScore) {
        this.allScore = allScore;
    }

    public void addScore(Score newScore){
        if (this.allScore == null)
            this.allScore = new ArrayList<>();
        this.allScore.add(0,newScore);
    }
    public void editScore(int position ,Score score){
        this.allScore.get(position).setAtServer(score.isAtServer());
        this.allScore.get(position).setId(score.getId());
        this.allScore.get(position).setScore(score.getScore());
        this.allScore.get(position).setStation(score.getStation());
        this.allScore.get(position).setDate(score.getDate());
        this.allScore.get(position).setFirstname(score.getFirstname());
        this.allScore.get(position).setLastname(score.getLastname());
        this.allScore.get(position).setTestStationID(score.getTestStationID());
        this.allScore.get(position).setTestCode(score.getTestCode());
    }

    public void logData(){
        int i=0;
        Log.d("userscore","================= Score =================");
        while (i<this.allScore.size()){
            Log.d("userscore", this.allScore.get(i).getId() + " " + this.allScore.get(i).isAtServer() + " " + this.allScore.get(i).getFirstname());
            i++;
        }
        Log.d("userscore","=========================================");
    }
}
