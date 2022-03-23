package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button Pro,Mod,Incrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button Etu = (Button) findViewById(R.id.add_etu);
        Pro = findViewById(R.id.ADD_TEACHER);
        Mod = findViewById(R.id.ADD_MODEL);
        Incrp = findViewById(R.id.ADD_registration);

        Etu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intention = new Intent(MainActivity.this, MainActivity_etu.class);
                startActivity(intention);
            }
        });

        Pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intention = new Intent(MainActivity.this, MainActivity_prof.class);
                startActivity(intention);
            }
        });

        Mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intention = new Intent(MainActivity.this, MainActivity_module.class);
                startActivity(intention);
            }
        });

        Incrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intention = new Intent(MainActivity.this, MainActivity_incp.class);
                startActivity(intention);
            }
        });

    }
}