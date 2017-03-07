package com.example.daini.calculadoraimc.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.daini.calculadoraimc.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(getClass().getCanonicalName(), "Estoy en MainActivity");

        startActivity(new Intent(this, LoginActivity.class));
    }
}
