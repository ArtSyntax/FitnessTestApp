package com.artsyntax.fitnesstest.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.artsyntax.fitnesstest.view.ResultList;

/**
 * Created by ArtSyntax on 17/2/2559.
 */
public class ResultListAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ResultList item;
        if (convertView != null)
            item = (ResultList) convertView;
        else
            item = new ResultList(parent.getContext());
        return item;
    }
}
