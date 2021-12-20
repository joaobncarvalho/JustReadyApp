package com.example.justreadyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.justreadyproject.downloaders.GetUserProfile;
import com.example.justreadyproject.downloaders.JSONobjDownloader;
import com.example.justreadyproject.downloaders.PostData;
import com.example.justreadyproject.login.LoginJustReady;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AddFriend extends AppCompatActivity {

    EditText mTextAddFriend;
    Button mButtonAdd;
    AsyncTask<String, Void, JSONObject> friend;
    public static String USER_TICKETADD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        mTextAddFriend = (EditText) findViewById(R.id.addticket);
        mButtonAdd = (Button) findViewById(R.id.addfriendbtn);
        JSONObject Friend = null;



        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetUserProfile getAddFriends = new GetUserProfile();
                Intent i = new Intent(AddFriend.this,FriendsList.class);

                try {

                    friend = getAddFriends.execute("https://justready.herokuapp.com/api/users/friendadd/"+ LoginJustReady.USER_TICKET);



                    JSONArray arr;
                    JSONObject friend ;



                    Map<String, String> postData = new HashMap<>();
                    postData.put("users_ticket", mTextAddFriend.getText().toString());
                    PostData task = new PostData(postData);
                    arr = task.execute("https://justready.herokuapp.com/api/users/friends1/"+ LoginJustReady.USER_TICKET).get();



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




    }
}