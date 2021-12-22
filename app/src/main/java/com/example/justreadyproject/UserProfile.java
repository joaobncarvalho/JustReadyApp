package com.example.justreadyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.justreadyproject.downloaders.GetUserProfile;
import com.example.justreadyproject.login.LoginJustReady;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UserProfile extends AppCompatActivity {

    TextView UserName,Gender1,Email1,Bdate1;
    ListView listviewuser;
    JSONObject JsonUser;
    ArrayList<String> Users, Usersid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setTitle("User");

        listviewuser = findViewById(R.id.UserProfile);
        Users = new ArrayList<String>();
        Usersid = new ArrayList<String>();



        try {
            GetUserProfile task = new GetUserProfile();
            JsonUser = task.execute("https://justready.herokuapp.com/api/users/"+ LoginJustReady.USER_ID).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            JsonUser = null;
        }


        if(JsonUser!=null)
        {
            try {
                String Usersname = JsonUser.getString("users_name");
                String UsersGender = JsonUser.getString("users_gender");
                String UsersEmail = JsonUser.getString("users_email");
                String UsersTicket = JsonUser.getString("users_ticket");
                String UsersBdate = JsonUser.getString("users_bdate");
                String UserLat = JsonUser.getString("users_locationlat");
                String UserLong = JsonUser.getString("users_locationlong");

                Users.add("Profile: " + Usersname + "\nGender: " + UsersGender +
                        "\nTicket Number: " + UsersTicket + "\nEmail: " + UsersEmail+"\nUser Lat: "+ UserLat+"\nUser Long: "+UserLong);
                Usersid.add(JsonUser.getString("users_id"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            initializeUserProfile();
        }

    }

    private void initializeUserProfile() {
        ArrayAdapter<String> UserAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Users);
        listviewuser.setAdapter(UserAdapter);

    }

}