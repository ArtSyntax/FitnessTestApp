package com.artsyntax.fitnesstest.manager;

import android.content.Context;

import com.artsyntax.fitnesstest.dao.StationListDao;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by ArtSyntax on 21/3/2559.
 */
public class StationListManager {
    private static StationListManager instance;

    public static StationListManager getInstance() {
        if (instance == null)
            instance = new StationListManager();
        return instance;
    }

    private Context mContext;

    private StationListDao dao;
    private StationListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public StationListDao getDao() {
        return dao;
    }

    public void setDao(StationListDao dao) {
        this.dao = dao;
    }

}
