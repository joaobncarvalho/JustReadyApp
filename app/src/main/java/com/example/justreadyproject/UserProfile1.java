package com.example.justreadyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.justreadyproject.downloaders.GetUserProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UserProfile1 extends AppCompatActivity {


    TextView UserName,Gender1,Email1,Bdate1;
    JSONObject JsonUser;
    ArrayList<String> Users, Usersid;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile1);


        Intent intent = getIntent();
        String name= intent.getStringExtra("name");
        String gender= intent.getStringExtra("gender");
        String email= intent.getStringExtra("email");
        String bdate= intent.getStringExtra("bdate");



        UserName=(TextView)findViewById(R.id.textViewName);
        Gender1=(TextView)findViewById(R.id.textViewGender);
        Email1=(TextView)findViewById(R.id.textViewEmail);
        Bdate1=(TextView)findViewById(R.id.textViewBdate);

        UserName.setText(name);
        Gender1.setText(gender);
        Email1.setText(email);
        Bdate1.setText(bdate);

    }



}

