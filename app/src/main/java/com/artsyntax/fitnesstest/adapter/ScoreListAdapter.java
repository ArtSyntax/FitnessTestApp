package com.artsyntax.fitnesstest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.artsyntax.fitnesstest.dao.ResultDao;
import com.artsyntax.fitnesstest.manager.ResultListManager;
import com.artsyntax.fitnesstest.manager.ScoreListManager;
import com.artsyntax.fitnesstest.manager.http.SQLManager;
import com.artsyntax.fitnesstest.utils.Score;
import com.artsyntax.fitnesstest.utils.ScoreList;
import com.artsyntax.fitnesstest.utils.TestInfo;
import com.artsyntax.fitnesstest.view.ResultList;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    List<Score> allUserScore = new ScoreList().getInstance();

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


        if (!allUserScore.get(position).isAtServer())
            submitScoreToServer(item,position);
        if (allUserScore.get(position).getFirstname() == null)
            item.setTextID(allUserScore.get(position).getId());
        else
            item.setTextID(allUserScore.get(position).getFirstname() + " " + allUserScore.get(position).getLastname());

        item.setTextScore(allUserScore.get(position).getScore());
        item.setTextStation(allUserScore.get(position).getStation());
        item.setTextDate(allUserScore.get(position).getDate());
        item.setBackgroundScoreList(allUserScore.get(position).isAtServer());
        return item;

    }

    private void submitScoreToServer( final ResultList item, final int position) {
        Log.d("userscore",position+"");
        ScoreListManager.getInstance().getData().logData();

        Call<ResultDao> call = SQLManager.getInstance().submitScore().submit(allUserScore.get(position).getTestCode(),
                allUserScore.get(position).getTestStationID(),allUserScore.get(position).getId(),allUserScore.get(position).getScore()+"");
        call.enqueue(new Callback<ResultDao>() {
            @Override
            public void onResponse(Call<ResultDao> call, Response<ResultDao> response) {
                if (response.isSuccess()) {
                    ResultDao dao = response.body();
                    ResultListManager.getInstance().setDao(dao);

                    if (dao.getFound().equals("1")) {               // found tag id in testcode
                        allUserScore.get(position).setAtServer(true);
                        allUserScore.get(position).setFirstname(dao.getFirstname());
                        allUserScore.get(position).setLastname(dao.getLastname());
                        item.setTextID(allUserScore.get(position).getFirstname() + " " + allUserScore.get(position).getLastname());
                        item.setBackgroundScoreList(allUserScore.get(position).isAtServer());
//                        clearToast();
//                        makeToast(allUserScore.get(position).getFirstname() + " " + allUserScore.get(position).getLastname());
                    } else {
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
