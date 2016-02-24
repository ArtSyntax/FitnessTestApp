package com.artsyntax.fitnesstest.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.artsyntax.fitnesstest.dao.PhotoItemDao;
import com.artsyntax.fitnesstest.manager.ResultListManager;
import com.artsyntax.fitnesstest.view.ResultList;

/**
 * Created by ArtSyntax on 17/2/2559.
 */
public class ResultListAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        if (ResultListManager.getInstance().getDao() == null)
            return 0;
        if (ResultListManager.getInstance().getDao().getData() == null)
            return 0;
        return ResultListManager.getInstance().getDao().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return ResultListManager.getInstance().getDao().getData().get(position);
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

        PhotoItemDao dao = (PhotoItemDao) getItem(position);
        item.setTextID(dao.getCaption());
        item.setTextDate(dao.getUserId()+"");
        return item;
    }
}
