package com.artsyntax.fitnesstest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.artsyntax.fitnesstest.R;
import com.artsyntax.fitnesstest.adapter.ResultListAdapter;

/**
 * Created by ArtSyntax on 27/1/2559.
 */
public class RecordingFragment extends Fragment implements View.OnClickListener {

    int someVar;

    EditText etID;
    EditText etScore;
    Button btSubmit;
    Button btStation;
    ListView listView;
    ResultListAdapter listAdapter;

    public static RecordingFragment newInstance(int someVar){
        RecordingFragment fragment = new RecordingFragment();
        Bundle args = new Bundle();
        args.putInt("tag1",someVar);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        someVar = getArguments().getInt("someVar"); // read args
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recording, container, false);
        initInstances(rootView);

        btSubmit.setOnClickListener(this);
        btStation.setOnClickListener(this);
        return rootView;
    }

    private void initInstances(View rootView) {
        etID = (EditText) rootView.findViewById(R.id.etID);
        etScore = (EditText) rootView.findViewById(R.id.etScore);
        btSubmit = (Button) rootView.findViewById(R.id.btSubmit);
        btStation = (Button) rootView.findViewById(R.id.btStation);
        listView = (ListView) rootView.findViewById(R.id.listView);
        listAdapter = new ResultListAdapter();
        listView.setAdapter(listAdapter);
    }

    public void setEditText(String text){
        etID.setText(text);
        etScore.setText(text);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.contentContainer);
        if (v == btSubmit) { // send score to server
            Log.d("submit", "submit ok");
        }
        if (v == btStation) { // send score to server
            if (fragment instanceof StationFragment == false) {
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
}
