package com.example.justreadyproject.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.justreadyproject.MainActivity;
import com.example.justreadyproject.R;
import com.example.justreadyproject.downloaders.JSONArrDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginJustReady extends AppCompatActivity {


    JSONArray LoginCredentials = null;
    EditText mTextTicketnum;
    EditText mTextPassword;
    Button mButtonLogin;
    Button mButtonRegister;
    public static String USER_ID;
    public static String USER_TICKET;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_just_ready);

        mTextTicketnum = (EditText) findViewById(R.id.tick_number);
        mTextPassword = (EditText) findViewById(R.id.password);
        mButtonLogin = (Button) findViewById(R.id.register);
        mButtonRegister = (Button) findViewById(R.id.Registerlogin);

        mTextTicketnum.setText("100");
        mTextPassword.setText("joaoterrorista2002");


        JSONArrDownloader task = new JSONArrDownloader();

        LoginCredentials = new JSONArray();
        try {
            LoginCredentials = task.execute("https://justready.herokuapp.com/api/users").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("credentials:" , ""+ LoginCredentials) ;



        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginJustReady.this, RegisterJustReady.class);
                startActivity(i);
            }
        });
    }

    public void onClickLogin(View v) throws JSONException {

        JSONArray test = new JSONArray();
        String Ticket = mTextTicketnum.getText().toString();
        String Password = mTextPassword.getText().toString();



        JSONObject User;

        for (int i = 0; i < LoginCredentials.length(); i++) {
            User = LoginCredentials.getJSONObject(i);


            if (User.getString("users_ticket").equals(Ticket) && User.getString("users_password").equals(Password)) {

                USER_ID = User.getString("users_id");
                USER_TICKET = User.getString("users_ticket");

                Intent intent = new Intent(LoginJustReady.this, MainActivity.class);
                startActivity(intent);
                Log.e(String.valueOf(this), LoginCredentials.get(i).toString());

            } else if (User.getString("users_ticket").isEmpty() && User.getString("users_password").isEmpty()) {
                Toast.makeText(this, "Credenciais erradas!!! Verifique se está tudo bem!!!", Toast.LENGTH_SHORT).show();
                mTextTicketnum.setHintTextColor(Color.RED);
            }else if (User.getString("users_ticket").isEmpty()){
                Toast.makeText(this, "Credenciais erradas!!! Verifique se está tudo bem!!!", Toast.LENGTH_SHORT).show();
            }else if (User.getString("users_password").isEmpty()){
                Toast.makeText(this, "Credenciais erradas!!! Verifique se está tudo bem!!!", Toast.LENGTH_SHORT).show();
                mTextPassword.setHintTextColor(Color.RED);
            }else if (Ticket.isEmpty() || Password.isEmpty()) {
                Toast.makeText(this, "Credenciais erradas!!! Verifique se está tudo bem!!!", Toast.LENGTH_SHORT).show();
            }

        }



    }







    }




