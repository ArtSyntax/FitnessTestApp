package com.artsyntax.fitnesstest.manager;

import android.content.Context;

import com.artsyntax.fitnesstest.utils.ScoreList;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by ArtSyntax on 27/3/2559.
 */
public class ScoreListManager {
    private static ScoreListManager instance;

    public static ScoreListManager getInstance() {
        if (instance == null)
            instance = new ScoreListManager();
        return instance;
    }

    private Context mContext;

    private ScoreList allScore;
    private ScoreListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public ScoreList getData() {
        return allScore;
    }

    public void setAllScore(ScoreList allScore) {
        this.allScore = allScore;
    }
}
