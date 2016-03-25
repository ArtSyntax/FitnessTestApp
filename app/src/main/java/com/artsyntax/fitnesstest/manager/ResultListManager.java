package com.artsyntax.fitnesstest.manager;

import android.content.Context;

import com.artsyntax.fitnesstest.dao.ResultDao;
import com.artsyntax.fitnesstest.dao.StationListDao;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by ArtSyntax on 21/3/2559.
 */
public class ResultListManager {
    private static ResultListManager instance;

    public static ResultListManager getInstance() {
        if (instance == null)
            instance = new ResultListManager();
        return instance;
    }

    private Context mContext;

    private ResultDao dao;
    private ResultListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public ResultDao getDao() {
        return dao;
    }

    public void setDao(ResultDao dao) {
        this.dao = dao;
    }

}
