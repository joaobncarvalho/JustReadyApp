package com.example.justreadyproject.Palcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.justreadyproject.R;

public class PalcoLGbyMegaHits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palco_lgby_mega_hits);

        Intent intent1 = getIntent();

        String id = intent1.getStringExtra("id");
        String title = intent1.getStringExtra("Palco Lg By MegaHits");

    }
}