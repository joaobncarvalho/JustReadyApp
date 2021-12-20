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

    private ArrayList<String> username;
    private ArrayList<String> gender;
    private ArrayList<String> email;
    private ArrayList<String> ticket;
    private ArrayList<String> bdate;
    private ArrayAdapter<String> FriendsAdapter;
    public static String NAME;
    public static String GENDER;
    public static String EMAIL;
    public static String BDATE;


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


        Log.e("INFO",LoginJustReady.USER_ID);

        try {
            JsonFriends = task.execute("https://justready.herokuapp.com/api/users/friends1/"+ LoginJustReady.USER_TICKET).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }


        username=new ArrayList<>();
        gender=new ArrayList<>();
        email=new ArrayList<>();
        ticket=new ArrayList<>();
        bdate=new ArrayList<>();


        if(JsonFriends!=null)
        {
            for(int i=0;i<JsonFriends.length();i++){

                try {

                    friends= JsonFriends.getJSONObject(i);
                    String Usersname = friends.getString("users_name");
                    String UsersGender = friends.getString("users_gender");
                    String UsersEmail = friends.getString("users_email");
                    String UsersBdate = friends.getString("users_bdate");

                    username.add(friends.getString("users_name"));
                    gender.add(friends.getString("users_gender"));
                    email.add(friends.getString("users_email"));
                    bdate.add(friends.getString("users_bdate"));


                    Friends.add(String.format("- Name: %s\n - Gender: %s\n - Email: %s\n - Birthday: %s\n", Usersname,UsersGender,UsersEmail,UsersBdate));


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
        createListClickItem(listviewfriends,username,gender,email,bdate);


    }

    private void createListClickItem(ListView listviewfriends, ArrayList<String> username, ArrayList<String> gender, ArrayList<String> email, ArrayList<String> bdate) {
    listviewfriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
            Intent intent = new Intent(FriendsList.this, UserProfile1.class);
            NAME= username.get(i);
            GENDER= gender.get(i);
            EMAIL= email.get(i);
            BDATE= bdate.get(i);

            intent.putExtra("name",NAME);
            intent.putExtra("gender",GENDER);
            intent.putExtra("email",EMAIL);
            intent.putExtra("bdate",BDATE);
            Log.e("Info",NAME);
            startActivity(intent);
        }
    });


    }

}