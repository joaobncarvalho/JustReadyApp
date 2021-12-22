package com.example.justreadyproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Settings extends AppCompatActivity {


    Switch switchCompat;
    Switch switchCompat2;
    Switch switchCompat3;
    final int GALLERY_REQUEST=1;
    final int LOCATION_REQUEST=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchCompat =findViewById(R.id.switchnot);
        switchCompat2 = findViewById(R.id.acessph);
        switchCompat3 = findViewById(R.id.location);


        switchCompat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchCompat2.isChecked())
                    askPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},GALLERY_REQUEST);
            }
        });


        switchCompat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchCompat3.isChecked())
                    askPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST);
            }
        });


        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchCompat.isChecked()){
                    requestStoragePermission();
                }else{
                    turnOffSettings();
                }
            }
        });
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        int permissionChecker = PackageManager.PERMISSION_GRANTED;

        for(int permission:grantResults)
            permissionChecker= permissionChecker+permission;
        if(permissionChecker==PackageManager.PERMISSION_GRANTED&&grantResults.length>0)
            onPermissionGranted(requestCode);
        else{
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
        }
    }

    private void onPermissionGranted(int requestCode) {
        switch(requestCode){
            case GALLERY_REQUEST:

                Toast.makeText(this,"Photos Permission Granted",Toast.LENGTH_SHORT).show();
                break;

            case LOCATION_REQUEST:
                Toast.makeText(this,"Location Permission Granted",Toast.LENGTH_SHORT).show();
                break;

        }

    }

    private void askPermission(String[] strings, int requestCode) {
        if(Build.VERSION.SDK_INT>=23){
            int permissionChecker = PackageManager.PERMISSION_GRANTED;

            for(String permissionStr:strings)
                permissionChecker=permissionChecker+ ContextCompat.checkSelfPermission(getApplicationContext(),permissionStr);

            if(permissionChecker!=PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(this,strings,requestCode);
            else
                onPermissionGranted(requestCode);
        }else{
            onPermissionGranted(requestCode);
        }
    }

    private void turnOffSettings() {
        Toast.makeText(this,"Notifications OFF", Toast.LENGTH_SHORT).show();
    }

    @RequiresPermission(Manifest.permission.ACCESS_NOTIFICATION_POLICY)
    private void requestStoragePermission() {
        Toast.makeText(this,"Notifications ON", Toast.LENGTH_SHORT).show();

    }



}