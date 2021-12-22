package com.example.justreadyproject;


import static com.example.justreadyproject.AddFriend.USER_TICKETADD;
import static com.example.justreadyproject.AddFriend.userId2;
import static com.example.justreadyproject.AddFriend.userIdticket;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.justreadyproject.downloaders.GetUserProfile;
import com.example.justreadyproject.downloaders.JSONArrDownloader;
import com.example.justreadyproject.login.LoginJustReady;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UserProfile1 extends AppCompatActivity {

    Button requestLocation;
    TextView UserName,Gender1,Email1,Bdate1,LocLat,LocLong;
    JSONObject JsonUser;
    ArrayList<String> Users, Usersid;
    public String ID;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile1);


        Intent intent = getIntent();
        ID=intent.getStringExtra("id");
        String name= intent.getStringExtra("name");
        String gender= intent.getStringExtra("gender");
        String email= intent.getStringExtra("email");
        String bdate= intent.getStringExtra("bdate");
        String lat= intent.getStringExtra("lat");
        String longi = intent.getStringExtra("long");



        UserName=(TextView)findViewById(R.id.textViewName);
        Gender1=(TextView)findViewById(R.id.textViewGender);
        Email1=(TextView)findViewById(R.id.textViewEmail);
        Bdate1=(TextView)findViewById(R.id.textViewBdate);
        LocLat=(TextView)findViewById(R.id.textViewLat);
        LocLong=(TextView)findViewById(R.id.textViewLong);
        requestLocation= (Button) findViewById(R.id.RequestLocation);

        UserName.setText(name);
        Gender1.setText(gender);
        Email1.setText(email);
        Bdate1.setText(bdate);






        requestLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONArrDownloader task = new JSONArrDownloader();
                    JSONArray JSONResult = task.execute("https://justready.herokuapp.com/api/users/friendadd/"+ FriendsList.TICKET).get();
                    JSONObject obj;
                    Log.e("ticket",FriendsList.TICKET);
                    Log.e("INfo3", String.valueOf(JSONResult));
                    JSONResult.getJSONObject(0).getString("users_locationlat");
                    LocLat.setText(JSONResult.getJSONObject(0).getString("users_locationlat"));
                    LocLong.setText(JSONResult.getJSONObject(0).getString("users_locationlong"));


                } catch (ExecutionException | InterruptedException | JSONException e) {
                    e.printStackTrace();
                    JsonUser = null;
                }

            }
        });
    }

}

