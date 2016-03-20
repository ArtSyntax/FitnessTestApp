package com.artsyntax.fitnesstest.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.artsyntax.fitnesstest.dao.StationDao;
import com.artsyntax.fitnesstest.manager.StationListManager;
import com.artsyntax.fitnesstest.view.StationList;

/**
 * Created by ArtSyntax on 17/2/2559.
 */
public class StationListAdapter extends BaseAdapter {

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
    public View getView(int position, View convertView, ViewGroup parent) {
        StationList item;
        if (convertView != null)
            item = (StationList) convertView;
        else
            item = new StationList(parent.getContext());

        StationDao dao = (StationDao) getItem(position);
        item.setTextStation(dao.getStationName());
        return item;
    }
}
