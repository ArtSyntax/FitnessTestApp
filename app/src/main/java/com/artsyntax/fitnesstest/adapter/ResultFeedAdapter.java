package com.artsyntax.fitnesstest.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.artsyntax.fitnesstest.view.ResultFeedCustomViewGroup;

/**
 * Created by ArtSyntax on 17/2/2559.
 */
public class ResultFeedAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return 1000;
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
        ResultFeedCustomViewGroup item;
        if (convertView != null)
            item = (ResultFeedCustomViewGroup) convertView;
        else
            item = new ResultFeedCustomViewGroup(parent.getContext());
        return item;
    }
}
