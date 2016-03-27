package com.artsyntax.fitnesstest.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artsyntax.fitnesstest.R;
import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;


public class ResultList extends BaseCustomViewGroup {

    private TextView tvResultListID;
    private TextView tvResultListScore;
    private TextView tvResultListStation;
    private TextView tvResultListDate;
    private LinearLayout loScoreList;

    public ResultList(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public ResultList(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public ResultList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public ResultList(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_result, this);
    }

    private void initInstances() {
        // findViewById here
        tvResultListID = (TextView) findViewById(R.id.tvResultListID);
        tvResultListScore = (TextView) findViewById(R.id.tvResultListScore);
        tvResultListStation = (TextView) findViewById(R.id.tvResultListStation);
        tvResultListDate = (TextView) findViewById(R.id.tvResultListDate);
        loScoreList = (LinearLayout) findViewById(R.id.loScoreList);

    }

    public void setTextID(String text) {
        tvResultListID.setText(text);
    }

    public void setTextScore(String text) {
        tvResultListScore.setText(text);
    }

    public void setTextStation(String text) {
        tvResultListStation.setText(text);
    }

    public void setTextDate(String text) {
        tvResultListDate.setText(text);
    }

    public void setBackgroundScoreList(boolean atServer){
        if(atServer)
            loScoreList.setBackgroundResource(R.color.colorFeed);
        else
            loScoreList.setBackgroundResource(R.color.colorFeedFail);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

}
