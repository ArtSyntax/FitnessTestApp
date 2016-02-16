package com.artsyntax.fitnesstest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.artsyntax.fitnesstest.R;
import com.artsyntax.fitnesstest.adapter.ResultFeedAdapter;

/**
 * Created by ArtSyntax on 27/1/2559.
 */
public class RecordingFragment extends Fragment {

    int someVar;

    EditText etID;
    EditText etScore;
    Button btSubmit;
    ScrollView svResult;
    ListView listView;
    ResultFeedAdapter listAdapter;

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
        return rootView;
    }

    private void initInstances(View rootView) {
        etID = (EditText) rootView.findViewById(R.id.etID);
        etScore = (EditText) rootView.findViewById(R.id.etScore);
        btSubmit = (Button) rootView.findViewById(R.id.btSubmit);
        svResult = (ScrollView) rootView.findViewById(R.id.svResult);
        listView = (ListView) rootView.findViewById(R.id.listView);
        listAdapter = new ResultFeedAdapter();
        listView.setAdapter(listAdapter);
    }

    public void setEditText(String text){
        etID.setText(text);
        etScore.setText(text);
    }
}
