package com.artsyntax.fitnesstest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.artsyntax.fitnesstest.R;
import com.artsyntax.fitnesstest.dao.TestNameDao;
import com.artsyntax.fitnesstest.manager.TestInfo;
import com.artsyntax.fitnesstest.manager.http.SQLManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener{
    EditText etTestCode;
    EditText etServerIp;
    Button btLogin;
    TextView tvCurrentTestCode;
    TestInfo testInfo;

    public LoginFragment() {
        super();
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initInstances(rootView);
        btLogin.setOnClickListener(this);
        return rootView;
    }

    private void initInstances(View rootView) {
        // init instance with rootView.findViewById here
        etTestCode = (EditText) rootView.findViewById(R.id.etTestCode);
        etServerIp = (EditText) rootView.findViewById(R.id.etServerIp);
        btLogin = (Button) rootView.findViewById(R.id.btLogin);
        tvCurrentTestCode = (TextView) rootView.findViewById(R.id.tvCurrentTestCode);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
    @Override
    public void onClick(View v) {
        final Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.contentContainer);
        if (v == btLogin && etTestCode!=null) {
            testInfo.setTestCode(etTestCode.getText().toString());
            testInfo.setServerIp(etServerIp.getText().toString());
            tvCurrentTestCode.setText(testInfo.getTestCode());

            etTestCode.setText(null);

            Call<TestNameDao> call = SQLManager.getInstance().getCheckTestCode().checkTestCode();
            call.enqueue(new Callback<TestNameDao>() {
                @Override
                public void onResponse(Call<TestNameDao> call, Response<TestNameDao> response) {
                    if (response.isSuccess()) {
                        TestNameDao dao = response.body();

                        if (dao.getFound().toString().equals("1")){           // found testcode goto recording fragment
                            testInfo.setTestName(dao.getTestName());
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.contentContainer, RecordingFragment.newInstance())
                                    .commit();
                            Toast.makeText(getActivity(),
                                    testInfo.getTestName(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }

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
                public void onFailure(Call<TestNameDao> call, Throwable t) {     // cannot connect server

                    Toast.makeText(getActivity(),
                            t.toString(),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }
    }
}
