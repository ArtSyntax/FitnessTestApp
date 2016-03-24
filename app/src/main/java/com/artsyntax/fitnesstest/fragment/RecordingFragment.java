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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.artsyntax.fitnesstest.R;
import com.artsyntax.fitnesstest.adapter.ResultListAdapter;
import com.artsyntax.fitnesstest.dao.PhotoItemCollectionDao;
import com.artsyntax.fitnesstest.manager.ResultListManager;
import com.artsyntax.fitnesstest.manager.TestInfo;
import com.artsyntax.fitnesstest.manager.http.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ArtSyntax on 27/1/2559.
 */
public class RecordingFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {

    int someVar;

    EditText etID;
    EditText etScore;
    Button btSubmit;
    Button btStation;
    ListView listView;
    ResultListAdapter listAdapter;
    TestInfo testInfo;

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

        someVar = getArguments().getInt("someVar"); // read args
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
        InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(v.getContext().INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void initInstances(View rootView) {
        etID = (EditText) rootView.findViewById(R.id.etID);
        etScore = (EditText) rootView.findViewById(R.id.etScore);
        btSubmit = (Button) rootView.findViewById(R.id.btSubmit);
        btStation = (Button) rootView.findViewById(R.id.btStation);
        listView = (ListView) rootView.findViewById(R.id.listView);
        listAdapter = new ResultListAdapter();
        listView.setAdapter(listAdapter);


        Call<PhotoItemCollectionDao> call = HttpManager.getInstance().getService().loadPhotoList();
        call.enqueue(new Callback<PhotoItemCollectionDao>() {
            @Override
            public void onResponse(Call<PhotoItemCollectionDao> call, Response<PhotoItemCollectionDao> response) {
                if (response.isSuccess()) {
                    PhotoItemCollectionDao dao = response.body();
                    ResultListManager.getInstance().setDao(dao);
                    listAdapter.notifyDataSetChanged();
                } else {              // 404 not found
                    try {
                        Toast.makeText(getActivity(),
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT)
                                .show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PhotoItemCollectionDao> call, Throwable t) {     // cannot connect server

                Toast.makeText(getActivity(),
                        t.toString(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
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

        // TODO send score to server

    }

}
