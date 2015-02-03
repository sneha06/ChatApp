package com.android.sneha.chatapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class ChatActivity extends ActionBarActivity {

    public  static final String CLASS_MESSAGES = "Message";
    public static final String KEY_RECIPIENT_IDS = "recipientIds";
    public static final String KEY_SENDER_IDS = "senderId";
    public static final String KEY_SENDER_NAME = "senderName";

    Button sendButton;
    EditText sendtext;

    String recipientId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recipientId = getIntent().getStringExtra("ruid");

        String UserChatDB = ParseUser.getCurrentUser().getUsername() + " - "  ;
        ;

        sendtext = (EditText) findViewById(R.id.sendText);
        sendButton = (Button) findViewById(R.id.sendbutton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ParseObject message = ParseObject.create(sendtext.getText().toString());
                ParseObject message = createMessage();
                if(message == null){
                    //error
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
                    builder.setMessage("there was an error with the file selected.Please select defferent file")
                    .setTitle("We're sorry")
                    .setPositiveButton(android.R.string.ok,null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    //send
                    send(message);
                    finish();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
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

    protected void send(ParseObject message){
        message.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    //success
                    Toast.makeText(ChatActivity.this,"Message sent!",Toast.LENGTH_LONG).show();
                }else{

                    AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
                    builder.setMessage("there was an error sending your message.Please try again")
                            .setTitle("We're sorry")
                            .setPositiveButton(android.R.string.ok,null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        });
    }

    protected ParseObject createMessage(){
        ParseObject message = new ParseObject(sendtext.getText().toString());
        message.put(KEY_SENDER_IDS, ParseUser.getCurrentUser().getObjectId());
        message.put(KEY_SENDER_NAME,ParseUser.getCurrentUser().getUsername());
        message.put(KEY_RECIPIENT_IDS,recipientId);

        return message;
    }



}
