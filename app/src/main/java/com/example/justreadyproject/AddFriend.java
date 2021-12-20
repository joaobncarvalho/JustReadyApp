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
import com.example.justreadyproject.downloaders.JSONArrDownloader;
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
    public static String USER_TICKETADD;
    public static String USER_IDFRIEND;
    public static String USER_NAMEID;
    public static String USER_RELNAMEID;
    public static String USER_GENDER;
    JSONObject Friend ;
    JSONArray Friendarr;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        mTextAddFriend = (EditText) findViewById(R.id.addticket);
        mButtonAdd = (Button) findViewById(R.id.addfriendbtn);






        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArrDownloader getAddFriends = new JSONArrDownloader();
                Intent i = new Intent(AddFriend.this,FriendsList.class);
                USER_RELNAMEID = "1";
                USER_IDFRIEND ="6";
                try {

                    USER_TICKETADD = mTextAddFriend.getText().toString();

                    Friendarr = getAddFriends.execute("https://justready.herokuapp.com/api/users/friendadd/"+ USER_TICKETADD).get();



                    Log.e("Info Ticket",USER_TICKETADD);

                    try{
                        Map<String, String> postData = new HashMap<>();

                        postData.put("rl_rel_nameid",USER_RELNAMEID);
                        postData.put("rl_users_ticket",USER_TICKETADD);
                        postData.put("rl_users_idmain",LoginJustReady.USER_ID);
                        postData.put("rl_users_idfriend",USER_IDFRIEND);


                        PostData task = new PostData(postData);
                        Log.e("Info1", String.valueOf(Friendarr));
                        Friendarr = task.execute("https://justready.herokuapp.com/api/rs").get();


                    }catch(Exception e){
                        e.printStackTrace();
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                    Friend = null;
                }
            }
        });




    }
}