package com.android.sneha.chatapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.AppEventsLogger;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable;


public class LoginActivity extends FragmentActivity {


    public static final String EXTRA_ACCOUNTNAME = "extra_accountname";
    SignInButton mSignInButton;
    private static final String TAG = "LoginActivity";
    static final int REQUEST_CODE_PICK_ACCOUNT = 1000;
    static final int REQUEST_CODE_RECOVER_FROM_AUTH_ERROR = 1001;

    public String mEmail;

    private static final String DEBUG_TAG = "NetworkStatus";

    private MainFragment mainFragment;

    // Received from newChooseAccountIntent(); passed to getToken()
    private static final String SCOPE =
            "oauth2:https://www.googleapis.com/auth/userinfo.profile";

    static final int REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        Parse.enableLocalDatastore(LoginActivity.this);
//
//        Parse.initialize(LoginActivity.this, "HxSlXVJeOLaQGqYceL42N3K3PSEObXekDCoEEyCR", "Sk5cIELK1rfjEmtvRcep2n5Hxp1NJKZ4TH6kaTa2");



        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        //mSignInButton.isInEditMode();


        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, mainFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            mainFragment = (MainFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }


        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.org.package", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String sign = Base64
                        .encodeToString(md.digest(), Base64.DEFAULT);

                Log.e("MY KEY HASH:", sign);

            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
        if(isGooglePlayServicesAvailable(LoginActivity.this) == ConnectionResult.SUCCESS){

            Toast.makeText(this,"Google play services is Avialable",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
