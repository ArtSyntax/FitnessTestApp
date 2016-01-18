package com.example.artsyntax.fitnesstest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    Button btLogin;
    EditText etUsername;
    EditText etPassword;
    TextView tvRegister;
    TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initInstances();

        btLogin.setOnClickListener(this);
        etPassword.setOnEditorActionListener(this);
        tvForgotPassword.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    private void initInstances() {
        btLogin = (Button) findViewById(R.id.btLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
    }

    @Override
    public void onClick(View v) {
        if (v == btLogin) { // send score to server
            if (etUsername.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0) {
                Toast.makeText(LoginActivity.this, getString(R.string.toast_login_success), Toast.LENGTH_SHORT).show();
                this.finish();
            } else {
                Toast.makeText(LoginActivity.this, getString(R.string.toast_error_login), Toast.LENGTH_SHORT).show();
            }
        }
        if (v==tvForgotPassword){
            Toast.makeText(LoginActivity.this, getString(R.string.soon), Toast.LENGTH_SHORT).show();
        }
        if (v==tvRegister){
            Toast.makeText(LoginActivity.this, getString(R.string.soon), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_GO) {
            if (etUsername.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0) {
                Toast.makeText(LoginActivity.this, getString(R.string.toast_login_success), Toast.LENGTH_SHORT).show();
                this.finish();
                return true;
            } else {
                Toast.makeText(LoginActivity.this, getString(R.string.toast_error_login), Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_profile) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
