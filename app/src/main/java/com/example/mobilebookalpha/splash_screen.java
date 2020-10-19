package com.example.mobilebookalpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash_screen extends AppCompatActivity {
    private ImageView imageView,uptadeaide;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        uptadeaide=findViewById(R.id.imageView2);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anime);
        Animation sclaeanimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scalea_nime);
        imageView.setAnimation(sclaeanimation);
        textView.setAnimation(animation);
        uptadeaide.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(splash_screen.this, Login_page.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);

            }
        }, 5500);
    }
}