package com.example.mobilebookalpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Forgat_pass extends AppCompatActivity {
    private Button resetpassword;
    private EditText resetemailet;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgat_pass);

        auth = FirebaseAuth.getInstance();
        resetemailet = findViewById(R.id.resetemailet);
        resetpassword = findViewById(R.id.resetpassword);
        progressBar = findViewById(R.id.progressBar4);
        progressBar.setVisibility(View.INVISIBLE);
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resetemailStr = resetemailet.getText().toString();
                if (resetemailStr.equals("")) {
                    Toast.makeText(getApplicationContext(), "bu alan boş bırakılamaz!", Toast.LENGTH_LONG).show();
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.VISIBLE);
                            auth.sendPasswordResetEmail(resetemailStr)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "E-posta adresinize sıfırlama linki gönderildi", Toast.LENGTH_LONG).show();
                                                finish();
                                                overridePendingTransition(R.anim.turn_anim_in,R.anim.turn_anim_out);
                                            } else
                                                Toast.makeText(getApplicationContext(), "Lütfen geçerli bir e-posta adresi giriniz ", Toast.LENGTH_LONG).show();
                                                 progressBar.setVisibility(View.INVISIBLE);
                                        }
                                    });

                        }
                    }, 2000);

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.turn_anim_in, R.anim.turn_anim_out);
    }
}