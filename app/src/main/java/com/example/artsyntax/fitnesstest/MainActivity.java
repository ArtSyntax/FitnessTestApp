package com.example.artsyntax.fitnesstest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity
        extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener {

    EditText etID;
    EditText etScore;
    TextView tvResult;
    Button btSubmit;
    Spinner spStation;
    String[] stations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances(); //findViewById
        initStations(); //initDropdownMenu

        btSubmit.setOnClickListener(this);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onClick(View v) {
        if (v == btSubmit) { // send score to server
            if (etID.getText().toString().length() > 0 && etScore.getText().toString().length() > 0) {
                String result = tvResult.getText().toString();
                result = spStation.getSelectedItem().toString() + " \t" + "No. " + etID.getText().toString()
                        + getString(R.string.label_score_result) + etScore.getText().toString() + "\n" + result;
                tvResult.setText(result);
                etID.setText(null);
                etScore.setText(null);
                etID.setFocusableInTouchMode(true);
                etID.requestFocus();
                Toast.makeText(MainActivity.this, getString(R.string.toast_send_seccess), Toast.LENGTH_SHORT).show();
                //Log.d("submit", etID.getText().toString().length()+"");
            } else {
                Toast.makeText(MainActivity.this, getString(R.string.toast_null_input), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initStations() {
        stations = new String[]{"ส่วนสูง(ซ.ม.)", "น้ำหนัก(ก.ก.)", "งอตัว(ซ.ม.)", "ลุกนั่ง(ครั้ง)", "วิ่ง(นาที)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stations);
        spStation.setAdapter(adapter);
    }

    private void initInstances() {
        etID = (EditText) findViewById(R.id.etID);
        etScore = (EditText) findViewById(R.id.etScore);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btSubmit = (Button) findViewById(R.id.btSubmit);
        spStation = (Spinner) findViewById(R.id.spStation);
    }


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                changeTextview(getString(R.string.title_section1));
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                changeTextview(getString(R.string.title_section3));
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                changeTextview(getString(R.string.title_section4));
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                changeTextview(getString(R.string.title_section5));
                break;
        }
    }

    private void changeTextview(String text) {
        ((TextView) findViewById(R.id.text_session)).setText(text);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {

            getMenuInflater().inflate(R.menu.global, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_profile) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;


        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}


