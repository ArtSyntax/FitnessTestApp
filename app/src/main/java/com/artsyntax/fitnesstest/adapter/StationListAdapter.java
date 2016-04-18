package com.artsyntax.fitnesstest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.artsyntax.fitnesstest.R;
import com.artsyntax.fitnesstest.activity.MainActivity;
import com.artsyntax.fitnesstest.dao.StationDao;
import com.artsyntax.fitnesstest.fragment.RecordingFragment;
import com.artsyntax.fitnesstest.manager.StationListManager;
import com.artsyntax.fitnesstest.utils.TestInfo;
import com.artsyntax.fitnesstest.view.StationList;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by ArtSyntax on 17/2/2559.
 */
public class StationListAdapter extends BaseAdapter {
    TestInfo testInfo;
    private Context mContext;

    @Override
    public int getCount() {
        if (StationListManager.getInstance().getDao() == null)
            return 0;
        if (StationListManager.getInstance().getDao().getStations() == null)
            return 0;
        return StationListManager.getInstance().getDao().getStations().size();
    }

    @Override
    public Object getItem(int position) {
        return StationListManager.getInstance().getDao().getStations().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        StationList item;
        if (convertView != null)
            item = (StationList) convertView;
        else
            item = new StationList(parent.getContext());
        final StationDao dao = (StationDao) getItem(position);
        item.setTextStation(dao.getStationName());

        // handle station select
        item.setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("station", "position " + position);
                testInfo.setCurrentStationName(dao.getStationName());
                testInfo.setCurrentStationUnit(dao.getStationUnit());
                testInfo.setCurrentTestStationID(dao.getTestStationId());
                double tmpMin = Math.min(Double.valueOf(dao.getLowScoreBound()), Double.valueOf(dao.getHighScoreBound()));
                double tmpMax = Math.max(Double.valueOf(dao.getLowScoreBound()), Double.valueOf(dao.getHighScoreBound()));
                double scoreRange = (double) ((tmpMax-tmpMin)*2);
                testInfo.setHighScoreBound(tmpMax + scoreRange);
                if(tmpMin-scoreRange > 0){
                    testInfo.setLowScoreBound(0);
                }
                else{
                    testInfo.setLowScoreBound(tmpMin - scoreRange);
                }


                Toast.makeText(v.getContext(),
                        "ฐานทดสอบ : " + testInfo.getCurrentStationName(),
                        Toast.LENGTH_SHORT)
                        .show();
                ((MainActivity) v.getContext()).getSupportFragmentManager().popBackStack();
            }
        });
        return item;
    }
}
