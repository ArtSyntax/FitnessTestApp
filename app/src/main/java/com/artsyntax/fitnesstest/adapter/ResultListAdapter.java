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
import com.artsyntax.fitnesstest.manager.StationListManager;
import com.artsyntax.fitnesstest.utils.TestInfo;
import com.artsyntax.fitnesstest.view.ResultList;
import com.artsyntax.fitnesstest.view.StationList;

/**
 * Created by ArtSyntax on 17/2/2559.
 */
public class ResultListAdapter extends BaseAdapter {
    TestInfo testInfo;
    private Context mContext;

    @Override
    public int getCount() {
//        if (StationListManager.getInstance().getDao() == null)
//            return 0;
//        if (StationListManager.getInstance().getDao().getStations() == null)
//            return 0;
//        return StationListManager.getInstance().getDao().getStations().size();
        return 2;
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
        ResultList item;
        if (convertView != null)
            item = (ResultList) convertView;
        else
            item = new ResultList(parent.getContext());

//        final ResultDao dao = (ResultDao) getItem(position);
//        item.setTextDate(dao.getUserId());

        return item;
    }
}
