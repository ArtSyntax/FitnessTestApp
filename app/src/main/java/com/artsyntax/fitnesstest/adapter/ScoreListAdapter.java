package com.artsyntax.fitnesstest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.artsyntax.fitnesstest.activity.MainActivity;
import com.artsyntax.fitnesstest.dao.ResultDao;
import com.artsyntax.fitnesstest.dao.StationDao;
import com.artsyntax.fitnesstest.manager.ResultListManager;
import com.artsyntax.fitnesstest.manager.ScoreListManager;
import com.artsyntax.fitnesstest.manager.StationListManager;
import com.artsyntax.fitnesstest.manager.http.SQLManager;
import com.artsyntax.fitnesstest.utils.Score;
import com.artsyntax.fitnesstest.utils.TestInfo;
import com.artsyntax.fitnesstest.view.ResultList;
import com.artsyntax.fitnesstest.view.StationList;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ArtSyntax on 17/2/2559.
 */
public class ScoreListAdapter extends BaseAdapter {
    TestInfo testInfo;
    private Context mContext;
    private ArrayList<Toast> toast = new ArrayList<Toast>();
    Score userScore;

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

        userScore = (Score) getItem(position);


        submitScoreToServer(item);
        if (userScore.getFirstname() == null)
            item.setTextID(userScore.getId());

        item.setTextScore(userScore.getScore());
        item.setTextStation(userScore.getStation());
        item.setTextDate(userScore.getDate());
        item.setBackgroundScoreList(userScore.isAtServer());
        ScoreListManager.getInstance().getData().logData();
        return item;

    }

    private void submitScoreToServer( final ResultList item) {
        Log.d("userscore", "1>> " + userScore.getId() + " " + userScore.isAtServer());
        Call<ResultDao> call = SQLManager.getInstance().submitScore().submit(userScore.getTestCode(),
                userScore.getTestStationID(),userScore.getId(),userScore.getScore()+"");
        call.enqueue(new Callback<ResultDao>() {
            @Override
            public void onResponse(Call<ResultDao> call, Response<ResultDao> response) {
                if (response.isSuccess()) {
                    ResultDao dao = response.body();
                    ResultListManager.getInstance().setDao(dao);

                    if (dao.getFound().equals("1")) {               // found tag id in testcode
                        userScore.setAtServer(true);
                        userScore.setFirstname(dao.getFirstname());
                        userScore.setLastname(dao.getLastname());
                        item.setTextID(userScore.getFirstname() + " " + userScore.getLastname());
                        item.setBackgroundScoreList(userScore.isAtServer());
                        clearToast();
                        //makeToast(userScore.getFirstname() + " " + userScore.getLastname());
                    }
                    else {
                        clearToast();
                        makeToast("ไม่พบหมายเลขผู้ทดสอบ");
                    }
                } else {                              // 404 not found
                    try {
                        clearToast();
                        makeToast("เกิดข้อผิดพลาด");
                        Log.d("Error! 404 Not found: ", response.errorBody().string());      // error message
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultDao> call, Throwable t) {     // cannot connect server
                clearToast();
                makeToast("เครือข่ายมีปัญหา!");
                Log.d("Error! no server: ", t.toString());         // error message
            }
        });
    }

    private void clearToast() {
        for (Toast t : toast) {
            if (t != null) {
                t.cancel();
            }
        }
        toast.clear();
    }

    private void makeToast(String text) {
        Toast t = Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_LONG);
        t.show();
        toast.add(t);
    }
}
