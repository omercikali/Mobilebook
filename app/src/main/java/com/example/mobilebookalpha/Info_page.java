package com.example.mobilebookalpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Info_page extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);
        imageView = findViewById(R.id.back_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Info_page.this,MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.turn_anim_in,R.anim.turn_anim_out);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.turn_anim_in, R.anim.turn_anim_out);
    }
}