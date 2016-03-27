package com.artsyntax.fitnesstest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.artsyntax.fitnesstest.R;
import com.artsyntax.fitnesstest.dao.TestNameDao;
import com.artsyntax.fitnesstest.utils.TestInfo;
import com.artsyntax.fitnesstest.manager.http.SQLManager;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {
    EditText etTestCode;
    EditText etServerIp;
    Button btLogin;
    TextView tvCurrentTestCode;
    TestInfo testInfo;
    private ArrayList<Toast> toast = new ArrayList<Toast>();

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
        hiddenKeyboard(rootView);
        btLogin.setOnClickListener(this);
        etServerIp.setOnEditorActionListener(this);
        etTestCode.setText(testInfo.getTestCode());
        etServerIp.setText(testInfo.getServerIp());
        if (testInfo.getTestName() != null) {
            tvCurrentTestCode.setText(testInfo.getTestName() + "\n (" + testInfo.getTestCode() + ")");
            etTestCode.setText(testInfo.getTestCode());
        } else {
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
        //final Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.contentContainer);
        if (validateInput() && actionId == EditorInfo.IME_ACTION_GO) {
            submitTestCode();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (validateInput() && v == btLogin) {
            submitTestCode();
        }
    }

    private boolean validateInput() {
        testInfo.setTestCode(etTestCode.getText().toString());
        testInfo.setServerIp(etServerIp.getText().toString());
        tvCurrentTestCode.setText(getResources().getString(R.string.input_test_code));

        if (etTestCode.length() < 6) {
            clearToast();
            makeToast("รหัสแบบทดสอบผิดพลาด");
            testInfo.setTestCode(null);
            etTestCode.setText(null);
            etTestCode.setFocusableInTouchMode(true);
            etTestCode.requestFocus();
            return false;
        } else if (etServerIp.length() < 7) {
            clearToast();
            makeToast("หมายเลขไอพีผิดพลาด\n" +
                    "เปลี่ยนกลับเป็นค่าเริ่มต้น");
            etServerIp.setText(getResources().getString(R.string.default_ip));
            etServerIp.setFocusableInTouchMode(true);
            etServerIp.requestFocus();
            return false;
        } else {
            clearToast();
            for (int i = 0; i < 10; i++) {     // toast LENGTH.LONG x10 time
                makeToast("กำลังเชื่อมต่อ...");
            }
        }
        return true;
    }

    private void makeToast(String text) {
        Toast t = Toast.makeText(getActivity(),
                text,
                Toast.LENGTH_LONG);
        t.show();
        toast.add(t);
    }

    private void clearToast() {
        for (Toast t : toast) {
            if (t != null) {
                t.cancel();
            }
        }
        toast.clear();
    }

    private void hiddenKeyboard(View v) {
        InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        try {
            keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private void submitTestCode() {
        Log.d("station", "IP : " + testInfo.getServerIp() + " Testcode : " + testInfo.getTestCode());
        testInfo.clear();
        testInfo.setTestCode(etTestCode.getText().toString());
        testInfo.setServerIp(etServerIp.getText().toString());
        Call<TestNameDao> call = SQLManager.getInstance().getCheckTestCode().checkTestCode(testInfo.getTestCode());
        call.enqueue(new Callback<TestNameDao>() {
            @Override
            public void onResponse(Call<TestNameDao> call, Response<TestNameDao> response) {
                if (response.isSuccess()) {
                    TestNameDao dao = response.body();

                    if (dao.getFound().toString().equals("1")) {           // found testcode goto recording fragment
                        testInfo.setTestName(dao.getTestName());
                        hiddenKeyboard(getView());
                        getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.contentContainer, RecordingFragment.newInstance())
                                .commit();
                        tvCurrentTestCode.setText(testInfo.getTestName() + " (" + testInfo.getTestCode() + ")");
                        clearToast();
                        makeToast(testInfo.getTestName());
                    } else {                                                   // test code not found
                        etTestCode.setFocusableInTouchMode(true);
                        etTestCode.requestFocus();
                        etTestCode.setText(null);
                        tvCurrentTestCode.setText(getResources().getString(R.string.input_test_code));
                        clearToast();
                        makeToast("ไม่พบรหัสแบบทดสอบ");
                    }

                } else {                                                    // 404 not found
                    try {
                        etTestCode.setFocusableInTouchMode(true);
                        etTestCode.requestFocus();
                        etTestCode.setText(null);
                        tvCurrentTestCode.setText(getResources().getString(R.string.input_test_code));
                        clearToast();
                        makeToast("ไม่พบรหัสแบบทดสอบ");
                        Log.d("Error! 404 Not found: ", response.errorBody().string());      // error message
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<TestNameDao> call, Throwable t) {     // cannot connect server
                etServerIp.setFocusableInTouchMode(true);
                etServerIp.requestFocus();
                etServerIp.setText(getResources().getString(R.string.default_ip));
                clearToast();
                makeToast("ไม่พบเซิฟเวอร์\n" +
                        "เปลี่ยนกลับเป็นค่าเริ่มต้น");
                Log.d("Error! no server: ", t.toString());         // error message
            }
        });
    }
}