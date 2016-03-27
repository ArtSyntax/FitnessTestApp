package com.artsyntax.fitnesstest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.artsyntax.fitnesstest.activity.MainActivity;
import com.artsyntax.fitnesstest.dao.StationDao;
import com.artsyntax.fitnesstest.manager.ScoreListManager;
import com.artsyntax.fitnesstest.manager.StationListManager;
import com.artsyntax.fitnesstest.utils.Score;
import com.artsyntax.fitnesstest.utils.TestInfo;
import com.artsyntax.fitnesstest.view.ResultList;
import com.artsyntax.fitnesstest.view.StationList;

/**
 * Created by ArtSyntax on 17/2/2559.
 */
public class ScoreListAdapter extends BaseAdapter {
    TestInfo testInfo;
    private Context mContext;

    @Override
    public int getCount() {
        if (ScoreListManager.getInstance().getData() == null)
            return 0;
        if (ScoreListManager.getInstance().getData().getAllScore() == null)
            return 0;
        return ScoreListManager.getInstance().getData().getAllScore().size();
    }

    @Override
    public Object getItem(int position) {
        return ScoreListManager.getInstance().getData().getAllScore().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ResultList item;
        if (convertView != null)
            item = (ResultList) convertView;
        else
            item = new ResultList(parent.getContext());

        final Score userScore = (Score) getItem(position);
        item.setTextID(userScore.getId());
        item.setTextScore(userScore.getScore());
        item.setTextStation(userScore.getStation());
        item.setTextDate(userScore.getDate());
        item.setBackgroundScoreList(userScore.isAtServer());
        return item;
    }
}
