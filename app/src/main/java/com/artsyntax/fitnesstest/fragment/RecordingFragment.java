package com.artsyntax.fitnesstest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.artsyntax.fitnesstest.R;
import com.artsyntax.fitnesstest.adapter.ResultListAdapter;
import com.artsyntax.fitnesstest.adapter.ScoreListAdapter;
import com.artsyntax.fitnesstest.dao.ResultDao;
import com.artsyntax.fitnesstest.manager.ResultListManager;
import com.artsyntax.fitnesstest.manager.ScoreListManager;
import com.artsyntax.fitnesstest.utils.Score;
import com.artsyntax.fitnesstest.utils.ScoreList;
import com.artsyntax.fitnesstest.utils.TestInfo;
import com.artsyntax.fitnesstest.view.ResultList;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ArtSyntax on 27/1/2559.
 */
public class RecordingFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {

    TestInfo testInfo;
    EditText etID;
    EditText etScore;
    Button btSubmit;
    Button btStation;
    ListView listView;
    ScoreListAdapter listAdapter;
    ScoreList allUserScore;


    public static RecordingFragment newInstance() {
        RecordingFragment fragment = new RecordingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static RecordingFragment newInstance(int someVar){
        RecordingFragment fragment = new RecordingFragment();
        Bundle args = new Bundle();
        args.putInt("tag1", someVar);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recording, container, false);
        hiddenKeyboard(rootView);
        initInstances(rootView);
        if (testInfo.getCurrentTestStationID() != null){
            btStation.setText(testInfo.getCurrentStationName());
            etScore.setHint(getResources().getString(R.string.label_score)+ " ("+testInfo.getCurrentStationUnit()+")");
        }

        btSubmit.setOnClickListener(this);
        btStation.setOnClickListener(this);
        etScore.setOnEditorActionListener(this);

        return rootView;
    }

    private void hiddenKeyboard(View v) {
        InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void initInstances(View rootView) {
        etID = (EditText) rootView.findViewById(R.id.etID);
        etScore = (EditText) rootView.findViewById(R.id.etScore);
        btSubmit = (Button) rootView.findViewById(R.id.btSubmit);
        btStation = (Button) rootView.findViewById(R.id.btStation);
        listView = (ListView) rootView.findViewById(R.id.listView);
        allUserScore = new ScoreList();
        listAdapter = new ScoreListAdapter();
        listView.setAdapter(listAdapter);
        ScoreListManager.getInstance().setAllScore(allUserScore);

        ableConnect();

        testInfo.logData();
    }

    private boolean ableConnect() {
        if (testInfo.getTestName()==null){
            Toast.makeText(getActivity(),
                    "กรุณาใส่รหัสแบบทดสอบใหม่",
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }


    public void setEditText(String text){
        etID.setText(text);
        etScore.setText(text);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        final Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.contentContainer);
        if (actionId == EditorInfo.IME_ACTION_GO) {
            Log.d("submit", "action go");
            submitScore();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.contentContainer);
        if (v == btSubmit) { // send score to server
            Log.d("submit", "button");
            submitScore();
        }
        if (v == btStation) { // open station fragment
            if (fragment instanceof StationFragment == false) {
                hiddenKeyboard(v);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(
                                R.anim.from_right, R.anim.to_left,
                                R.anim.from_left, R.anim.to_right
                        )
                        .replace(R.id.contentContainer, StationFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
            Log.d("submit", "submit ok");
        }
    }

    private void submitScore() {

        if(validateInput()) {
            addNewScore();
            listAdapter.notifyDataSetChanged();
        }
    }

    private void addNewScore() {
        Score newScore = new Score();
        SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");  // default ddMMyyyyhhmmss aa
        String format = s.format(new Date());

        testInfo.setUserTagId(etID.getText().toString());
        testInfo.setUserScore((float) (Math.round(Float.parseFloat(etScore.getText().toString()) * 100.0) / 100.0));
        testInfo.logData();

        newScore.setAtServer(false);
        newScore.setId(testInfo.getUserTagId());
        newScore.setScore(testInfo.getUserScore() + "");
        newScore.setStation(testInfo.getCurrentStationName());
        newScore.setDate(format);
        allUserScore.addScore(newScore);

        testInfo.setUserTagId(null);
        testInfo.setUserScore(0);
        etID.setText(null);
        etScore.setText(null);
        etID.setFocusableInTouchMode(true);
        etID.requestFocus();

    }


    private boolean validateInput() {
        if (!ableConnect()){
            return false;
        }
        else if (testInfo.getCurrentTestStationID()==null){
            Toast.makeText(getActivity(),
                    "กรุณาเลือกฐานการทดสอบ",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (etID.length()<1){
            etID.setFocusableInTouchMode(true);
            etID.requestFocus();
            Toast.makeText(getActivity(),
                    "ระบุหมายเลขผู้ทดสอบผิดพลาด",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (etScore.length()<1){
            etScore.setFocusableInTouchMode(true);
            etScore.requestFocus();
            Toast.makeText(getActivity(),
                    "ระบุคะแนนผิดพลาด",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Float.parseFloat(etScore.getText().toString())<testInfo.getLowScoreBound() ||
                Float.parseFloat(etScore.getText().toString())>testInfo.getHighScoreBound()){

            etScore.setText(null);
            etScore.setFocusableInTouchMode(true);
            etScore.requestFocus();
            Toast.makeText(getActivity(),
                    "คะแนนผิดปกติ\n"+
                            "ใส่ใหม่อีกครั้ง",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}