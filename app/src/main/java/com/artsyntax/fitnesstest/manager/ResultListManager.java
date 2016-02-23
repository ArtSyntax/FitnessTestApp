package com.artsyntax.fitnesstest.manager;

import android.content.Context;

import com.artsyntax.fitnesstest.dao.PhotoItemCollectionDao;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;


public class ResultListManager {

    private static ResultListManager instance;

    public static ResultListManager getInstance() {
        if (instance == null)
            instance = new ResultListManager();
        return instance;
    }

    private Context mContext;

    public PhotoItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
    }

    private PhotoItemCollectionDao dao;
    private ResultListManager() {
        mContext = Contextor.getInstance().getContext();
    }

}
