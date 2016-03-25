package com.artsyntax.fitnesstest.activity;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.artsyntax.fitnesstest.R;
import com.artsyntax.fitnesstest.fragment.LoginFragment;
import com.artsyntax.fitnesstest.fragment.RecordingFragment;
import com.artsyntax.fitnesstest.utils.TestInfo;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TestInfo testInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();
        if (savedInstanceState == null && testInfo.getTestName()==null) {
            // create fragment on activity
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, LoginFragment.newInstance())
                    .commit();
        }
        else if (savedInstanceState == null && testInfo.getTestName()!=null) {
            // create fragment on activity
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, RecordingFragment.newInstance())
                    .commit();
            Toast.makeText(this,
                    testInfo.getTestName(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        testInfo.setServerIp(getResources().getString(R.string.default_ip));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        if (savedInstanceState == null) {
            LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag("LoginFragment");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentContainer);

        switch (item.getItemId()) {
            case R.id.action_profile:
                if (fragment instanceof LoginFragment == false) {
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    hiddenKeyboard(findViewById(R.id.toolbar));
                    getSupportFragmentManager().beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.contentContainer, LoginFragment.newInstance())
                            .addToBackStack(null)
                            .commit();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hiddenKeyboard(View v) {
        InputMethodManager keyboard = (InputMethodManager) getSystemService(v.getContext().INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}