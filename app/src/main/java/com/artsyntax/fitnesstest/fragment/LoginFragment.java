package com.artsyntax.fitnesstest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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

public class LoginFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener{
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
        hiddenKeyboard(rootView);
        initInstances(rootView);
        btLogin.setOnClickListener(this);
        etServerIp.setOnEditorActionListener(this);
        etTestCode.setText(testInfo.getTestCode());
        etServerIp.setText(testInfo.getServerIp());
        if (testInfo.getTestCode() != null) {
            tvCurrentTestCode.setText(testInfo.getTestName()+"\n ("+testInfo.getTestCode()+")");
            etTestCode.setText(testInfo.getTestCode());
        }
        else{
            tvCurrentTestCode.setText(getResources().getString(R.string.input_test_code));
        }
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
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        final Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.contentContainer);
        if (actionId == EditorInfo.IME_ACTION_GO) {
            submitTestCode();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        final Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.contentContainer);
        if (etTestCode.length()<6){
            etTestCode.setFocusableInTouchMode(true);
            etTestCode.requestFocus();
            Toast.makeText(getActivity(),
                    "Invalid Testcode",
                    Toast.LENGTH_SHORT).show();
        }
        else if (etServerIp.length()<7){
            etServerIp.setFocusableInTouchMode(true);
            etServerIp.requestFocus();
            Toast.makeText(getActivity(),
                    "Invalid Server IP",
                    Toast.LENGTH_SHORT).show();
        }
        else if (v == btLogin) {
            submitTestCode();
        }
    }

    private void hiddenKeyboard(View v) {
        InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(v.getContext().INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void submitTestCode() {
        testInfo.setTestCode(etTestCode.getText().toString());
        testInfo.setServerIp(etServerIp.getText().toString());

        Log.d("station","IP : "+testInfo.getServerIp()+ " Testcode : "+testInfo.getTestCode());


        Call<TestNameDao> call = SQLManager.getInstance().getCheckTestCode().checkTestCode(testInfo.getTestCode());
        call.enqueue(new Callback<TestNameDao>() {
            @Override
            public void onResponse(Call<TestNameDao> call, Response<TestNameDao> response) {
                if (response.isSuccess()) {
                    TestNameDao dao = response.body();

                    if (dao.getFound().toString().equals("1")){           // found testcode goto recording fragment
                        testInfo.setTestName(dao.getTestName());
                        tvCurrentTestCode.setText(testInfo.getTestName() + " (" + testInfo.getTestCode() + ")");
                        hiddenKeyboard(getView());
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contentContainer, RecordingFragment.newInstance())
                                .commit();

                        // clear all back stack fragment
//                        FragmentManager fm = getActivity().getSupportFragmentManager();
//                        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
//                            fm.popBackStack();
//                        }

                        Toast.makeText(getActivity(),
                                testInfo.getTestName(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                    else{
                        etTestCode.setFocusableInTouchMode(true);
                        etTestCode.requestFocus();
                        Toast.makeText(getActivity(),
                                "Not Found Testcode",
                                Toast.LENGTH_SHORT).show();
                    }

                } else {              // 404 not found
                    try {
                        etTestCode.setText(null);

                        Toast.makeText(getActivity(),
                                "Not Found Testcode!",
                                Toast.LENGTH_SHORT)
                                .show();
                        Log.d("Error! 404 Not found: ",response.errorBody().string());      // error message
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<TestNameDao> call, Throwable t) {     // cannot connect server

                Toast.makeText(getActivity(),
                        "Cannot connect the server!",                     // print t.toString() for error message
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}