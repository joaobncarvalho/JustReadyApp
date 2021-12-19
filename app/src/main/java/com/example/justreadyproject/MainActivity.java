package com.example.justreadyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.justreadyproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void MaintoFriends (View v){

        Intent FriendsList = new Intent(getApplicationContext(), FriendsList.class);

        startActivity(FriendsList);
    }



    public void MaintoProfile (View v){

        Intent UserProfile = new Intent(getApplicationContext(), com.example.justreadyproject.UserProfile.class);

        startActivity(UserProfile);
    }


    public void MaintoFestivalMap (View v){

        Intent FMap = new Intent(getApplicationContext(), FestivalMap.class);

        startActivity(FMap);
    }

    public void MaintoSettings (View v){

        Intent Sett = new Intent(getApplicationContext(), Settings.class);

        startActivity(Sett);
    }
}