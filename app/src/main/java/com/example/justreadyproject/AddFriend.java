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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AddFriend extends AppCompatActivity {

    EditText mTextAddFriend;
    Button mButtonAdd;
    JSONArray usersId;
    ArrayList<Integer> userId;
    ArrayList<String> users;
    public static String USER_TICKETADD;
    public static String USER_IDFRIEND;
    public static String USER_NAMEID;
    public static String USER_RELNAMEID;
    public static String USER_GENDER;
    JSONObject Friend ;
    public static int userId2;
    JSONArray Friendarr;
    public static int userIdticket;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        mTextAddFriend = (EditText) findViewById(R.id.addticket);
        mButtonAdd = (Button) findViewById(R.id.addfriendbtn);

        JSONArrDownloader task1 = new JSONArrDownloader();
    try {
        usersId = task1.execute("https://justready.herokuapp.com/api/users").get();
    } catch (ExecutionException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    JSONObject obj;
    users = new ArrayList<>();
    userId = new ArrayList<>();
        if(usersId != null) {
        for (int i = 0; i < usersId.length();i++){
            try{
                obj = usersId.getJSONObject(i);
                String userId1 = obj.getString("users_id");

                userId.add(obj.getInt("users_id"));
                users.add(String.format("%s",userId1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }






        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArrDownloader getAddFriends = new JSONArrDownloader();
                Intent i = new Intent(AddFriend.this,FriendsList.class);
                USER_RELNAMEID = "1";


                try {

                    USER_TICKETADD = mTextAddFriend.getText().toString();

                    Friendarr = getAddFriends.execute("https://justready.herokuapp.com/api/users/friendadd/"+ USER_TICKETADD).get();
                    JSONObject obj;
                    if (Friendarr != null) {
                        for (int k = 0; k < Friendarr.length(); k++) {
                            try {
                                obj = Friendarr.getJSONObject(k);
                                userId2 = Integer.parseInt(obj.getString("users_id"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (Friendarr != null) {
                        for (int k = 0; k < Friendarr.length(); k++) {
                            try {
                                obj = Friendarr.getJSONObject(k);
                                userIdticket = Integer.parseInt(obj.getString("users_ticket"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }



                    Log.e("USER ID", String.valueOf(userId2));
                    Log.e("Info Ticket",USER_TICKETADD);

                    try{
                        Map<String, String> postData = new HashMap<>();

                        postData.put("rl_rel_nameid",USER_RELNAMEID);
                        postData.put("rl_users_ticket",LoginJustReady.USER_TICKET);
                        postData.put("rl_users_idmain",LoginJustReady.USER_ID);
                        postData.put("rl_users_idfriend", String.valueOf(userId2));


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