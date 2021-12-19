package com.example.justreadyproject.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.justreadyproject.MainActivity;
import com.example.justreadyproject.R;
import com.example.justreadyproject.downloaders.PostData;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterJustReady extends AppCompatActivity {


    ArrayAdapter<String> adapterGender;
    ArrayList<String> listGender;
    EditText mTextTicketnum;
    EditText mTextPassword;
    EditText mTextName;
    Spinner mSpinnerGender;
    EditText mTextAge;
    EditText mTextEmail;
    Button mButtonRegister;

    String postBDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_just_ready);

        mTextTicketnum = (EditText) findViewById(R.id.tick_number);
        mTextPassword = (EditText) findViewById(R.id.password);
        mTextName = (EditText) findViewById(R.id.name);
        mSpinnerGender = (Spinner) findViewById(R.id.GenderSpinner);
        mTextAge = (EditText) findViewById(R.id.Bdate);
        mTextEmail = (EditText) findViewById(R.id.email);
        mButtonRegister = (Button) findViewById(R.id.register);


        listGender = new ArrayList<>();

        listGender.add("Gender");
        listGender.add("M");
        listGender.add("F");
        listGender.add("B");


        adapterGender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listGender);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerGender.setAdapter(adapterGender);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        mTextAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterJustReady.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        postBDate = year+"-"+month+"-"+day;
                        String date = day+"/"+month+"/"+year;
                        mTextAge.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });


        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    mButtonRegister.setTextColor(Color.BLUE);

                    if (mTextName.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Preencha os campos a vermelho!", Toast.LENGTH_SHORT).show();
                        mTextName.setHintTextColor(Color.RED);
                    }
                    if (mTextTicketnum.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Preencha os campos a vermelho!", Toast.LENGTH_SHORT).show();
                        mTextTicketnum.setHintTextColor(Color.RED);
                    }
                    if (mTextPassword.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Preencha os campos a vermelho!", Toast.LENGTH_SHORT).show();
                        mTextPassword.setHintTextColor(Color.RED);
                    }
                    if (mTextEmail.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Preencha os campos a vermelho!", Toast.LENGTH_SHORT).show();
                        mTextEmail.setHintTextColor(Color.RED);
                    }
                    if (mTextAge.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Preencha os campos a vermelho!", Toast.LENGTH_SHORT).show();
                        mTextAge.setHintTextColor(Color.RED);
                    } else {
                        Map<String, String> postData = new HashMap<>();
                        postData.put("users_name", mTextName.getText().toString());
                        postData.put("users_ticket", mTextTicketnum.getText().toString());
                        postData.put("users_password", mTextPassword.getText().toString());
                        postData.put("users_email", mTextEmail.getText().toString());
                        postData.put("users_bdate", postBDate);
                        postData.put("users_gender", "M");


                        Log.e("info",postBDate);

                        JSONArray arr;
                        PostData task = new PostData(postData);
                        arr = task.execute("https://justready.herokuapp.com/api/users").get();

                        Toast.makeText(getApplicationContext(), "Welcome !"+ mTextName.getText().toString(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(RegisterJustReady.this, LoginJustReady.class);
                        startActivity(i);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });






    }
}