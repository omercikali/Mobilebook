package com.example.mobilebookalpha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ImageView logout,infoimg;
    private TextView workerstw, soldgoodtw, fertiliziertw, advantage_damagetw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        infoimg=findViewById(R.id.Info_Tw);
        workerstw = findViewById(R.id.Workers_pageTw);
        soldgoodtw = findViewById(R.id.SoldGoods_tw);
        fertiliziertw = findViewById(R.id.Fertilizier_page_tw);
        advantage_damagetw = findViewById(R.id.Advantage_damage_tw);
        logout = findViewById(R.id.logoutim);

        infoimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Info_page.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
        workerstw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Workers_page.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
        soldgoodtw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Soldgoods_page.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
        fertiliziertw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Fertilizier_page.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
        advantage_damagetw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Advantage_Damage_page.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Mobildefter");
                builder.setMessage("Çıkış yapmak istediğinize eminmisiniz ?");
                builder.setNegativeButton("hayır",null);
                builder.setPositiveButton("evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(MainActivity.this, Login_page.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.turn_anim_in,R.anim.turn_anim_out);
                    }
                });
                builder.show();

            }
        });


    }

}