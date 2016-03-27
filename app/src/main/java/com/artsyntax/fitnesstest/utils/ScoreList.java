package com.artsyntax.fitnesstest.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArtSyntax on 27/3/2559.
 */
public class ScoreList {

    private static List<Score> allScore;

    public static List<Score> getInstance() {
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
}
