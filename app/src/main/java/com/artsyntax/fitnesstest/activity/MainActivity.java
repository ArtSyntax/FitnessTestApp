package com.artsyntax.fitnesstest.activity;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.artsyntax.fitnesstest.R;
import com.artsyntax.fitnesstest.fragment.LoginFragment;
import com.artsyntax.fitnesstest.fragment.RecordingFragment;
import com.artsyntax.fitnesstest.fragment.ResultFragment;
import com.artsyntax.fitnesstest.fragment.StationFragment;

import junit.framework.Test;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        if (savedInstanceState == null) {
            // create fragment on activity
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, RecordingFragment.newInstance(123), "RecordingFragment") //optional
                    .commit();
        }
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        if (savedInstanceState == null) {
            RecordingFragment fragment = (RecordingFragment) getSupportFragmentManager().findFragmentByTag("RecordingFragment");
            fragment.setEditText(null);
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
                    getSupportFragmentManager().beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.contentContainer, LoginFragment.newInstance())
                            .addToBackStack(null)
                            .commit();
                    //Toast.makeText(MainActivity.this, "Test OK", Toast.LENGTH_SHORT).show();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
