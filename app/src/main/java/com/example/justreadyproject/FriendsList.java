package com.example.justreadyproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.justreadyproject.databinding.ActivityFriendsListBinding;
import com.example.justreadyproject.downloaders.GetUserProfile;
import com.example.justreadyproject.downloaders.JSONArrDownloader;
import com.example.justreadyproject.login.LoginJustReady;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FriendsList extends AppCompatActivity {

    private ArrayList<String> userid;
    private ArrayList<String> username;
    private ArrayList<String> gender;
    private ArrayList<String> email;
    private ArrayList<String> ticket;
    private ArrayList<String> bdate;
    private ArrayList<String> lat;
    private ArrayList<String> longi;
    private ArrayAdapter<String> FriendsAdapter;
    public static String ID;
    public static String TICKET;
    public static String NAME;
    public static String GENDER;
    public static String EMAIL;
    public static String BDATE;
    public static String LAT;
    public static String LONG;




    private ListView listviewfriends;
    private ArrayList<String>Friends,Friendsid;
    private JSONArray JsonFriends;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_friends_list);
        listviewfriends = findViewById(R.id.FriendsList);
        Friends = new ArrayList<String>();
        Friendsid = new ArrayList<String>();
        JSONObject friends;
        JSONArrDownloader task = new JSONArrDownloader();




        try {
            JsonFriends = task.execute("https://justready.herokuapp.com/api/users/friends1/"+ LoginJustReady.USER_TICKET).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        userid = new ArrayList<>();
        username=new ArrayList<>();
        ticket = new ArrayList<>();
        gender=new ArrayList<>();
        email=new ArrayList<>();
        ticket=new ArrayList<>();
        bdate=new ArrayList<>();
        lat = new ArrayList<>();
        longi= new ArrayList<>();


        if(JsonFriends!=null)
        {
            for(int i=0;i<JsonFriends.length();i++){

                try {

                    friends= JsonFriends.getJSONObject(i);

                    String Usersname = friends.getString("users_name");
                    String UsersGender = friends.getString("users_gender");
                    String UsersTicket = friends.getString("users_ticket");
                    String UsersEmail = friends.getString("users_email");
                    String UsersBdate = friends.getString("users_bdate");
                    String UsersLat = friends.getString("users_locationlat");
                    String UsersLong = friends.getString("users_locationlong");

                    userid.add(friends.getString("users_id"));
                    username.add(friends.getString("users_name"));
                    ticket.add(friends.getString("users_ticket"));
                    gender.add(friends.getString("users_gender"));
                    email.add(friends.getString("users_email"));
                    bdate.add(friends.getString("users_bdate"));
                    lat.add(friends.getString("users_locationlat"));
                    longi.add(friends.getString("users_locationlong"));



                    Friends.add(String.format("- Name: %s\n - Gender: %s\n - Ticket: %s\n - Birthday: %s\n -Lat: %s\n -Long: %s", Usersname,UsersGender,UsersTicket,UsersBdate,UsersLat,UsersLong));


            } catch (JSONException e) {
                e.printStackTrace();
            }

            }

            Log.e("Array List", Friends.toString());
            initializeUserProfile();




        }

    }
    public void AddFriend (View v){

        Intent i = new Intent(getApplicationContext(), AddFriend.class);

        startActivity(i);
    }
    private void initializeUserProfile() {
        FriendsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,Friends);
        listviewfriends.setAdapter(FriendsAdapter);
        createListClickItem(listviewfriends,userid,username,gender,email,bdate,lat,longi);


    }

    private void createListClickItem(ListView listviewfriends,ArrayList<String>userid, ArrayList<String> username, ArrayList<String> gender, ArrayList<String> email, ArrayList<String> bdate,ArrayList<String> lat,ArrayList<String> longi) {
    listviewfriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
            Intent intent = new Intent(FriendsList.this, UserProfile1.class);

            ID = userid.get(i);
            NAME= username.get(i);
            GENDER= gender.get(i);
            TICKET = ticket.get(i);
            EMAIL= email.get(i);
            BDATE= bdate.get(i);
            LAT = lat.get(i);
            LONG = longi.get(i);

            intent.putExtra("id",ID);
            intent.putExtra("name",NAME);
            intent.putExtra("ticket",TICKET);
            intent.putExtra("gender",GENDER);
            intent.putExtra("email",EMAIL);
            intent.putExtra("bdate",BDATE);
            intent.putExtra("lat",LAT);
            intent.putExtra("long",LONG);
            Log.e("Infoname",NAME);
            startActivity(intent);
        }
    });


    }

}