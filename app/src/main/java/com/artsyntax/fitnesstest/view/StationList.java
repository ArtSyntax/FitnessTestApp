package com.artsyntax.fitnesstest.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.artsyntax.fitnesstest.R;
import com.artsyntax.fitnesstest.manager.TestInfo;
import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;


public class StationList extends BaseCustomViewGroup {

    private Button btStationListName;

    public StationList(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public StationList(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public StationList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public StationList(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_station, this);
    }

    private void initInstances() {
        // findViewById here
        btStationListName = (Button) findViewById(R.id.btStationListName);

    }

    public void setTextStation(String text) {
        btStationListName.setText(text);
    }

    public void setButtonClickListener(View.OnClickListener listener) {
        btStationListName.setOnClickListener(listener);
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
