package com.android.sneha.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();

            }
        },4000);
    }

    //project id=capable-passage-830
    // clientkey pswd= notasecret
    /*CLIENT ID 612898075815-r9vraq1a6dt9chmfud462et1tq74ih3h.apps.googleusercontent.com
    EMAIL ADDRESS  612898075815-r9vraq1a6dt9chmfud462et1tq74ih3h@developer.gserviceaccount.com
    CERTIFICATE FINGERPRINTS f980df30dd8eefc2c3af6ba71fe5c109cf2b457d
    SHA1: 49:5D:05:DC:D2:DC:1C:79:CA:6E:F7:E3:C2:57:00:32:87:77:FC:45
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
