package com.android.sneha.chatapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUpActivity extends ActionBarActivity {


    protected EditText mUsername;
    protected EditText mPassword;
    protected EditText mEmail;
    protected Button mSignup;
    protected Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Parse.enableLocalDatastore(SignUpActivity.this);

        Parse.initialize(SignUpActivity.this, "HxSlXVJeOLaQGqYceL42N3K3PSEObXekDCoEEyCR", "Sk5cIELK1rfjEmtvRcep2n5Hxp1NJKZ4TH6kaTa2");

        mUsername = (EditText) findViewById(R.id.pname);
        mPassword = (EditText) findViewById(R.id.pwd_login);
        mEmail = (EditText) findViewById(R.id.email_login);
        mSignup = (Button) findViewById(R.id.sgnbttn);
        mLogin = (Button) findViewById(R.id.login);


        mSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String email = mEmail.getText().toString().trim();

                if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setTitle("Oops!").setMessage(R.string.error_msg)
                            .setPositiveButton(android.R.string.ok, null);

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    //create a new user!
                    setProgressBarIndeterminateVisibility(true);
                    ParseUser newuser = new ParseUser();
                    newuser.setUsername(username);
                    newuser.setPassword(password);
                    newuser.setEmail(email);

                    newuser.signUpInBackground(new SignUpCallback() {

                        @Override
                        public void done(ParseException e) {
                            setProgressBarIndeterminateVisibility(false);

                            if(e == null){
                                //success!
                               // ParseApplication.updatePasreInstallation(ParseUser.getCurrentUser());

                                Intent intent = new Intent(SignUpActivity.this, ChatActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setTitle("Oops!").setMessage(e.getMessage())
                                        .setPositiveButton(android.R.string.ok, null);

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
