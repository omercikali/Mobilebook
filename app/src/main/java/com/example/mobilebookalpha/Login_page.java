package com.example.mobilebookalpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login_page extends AppCompatActivity {

    private Button withgoogleaccount, loginbutton, telefon_no_ile;
    private EditText emailet, passwordet;
    private FirebaseAuth auth;
    private TextView forgatmypass;
    private ProgressBar progressBar;
    private CallbackManager mCallbackManager;
    private static final String TAG = "facebook ";
    private FirebaseAuth fb_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        telefon_no_ile = findViewById(R.id.telefon_no_ile);
        progressBar = findViewById(R.id.progressBar);
        withgoogleaccount = findViewById(R.id.withgooglebtn);
        loginbutton = findViewById(R.id.button);
        forgatmypass = findViewById(R.id.forgatmypass);
        emailet = findViewById(R.id.emailet);
        passwordet = findViewById(R.id.passwordet);

        auth = FirebaseAuth.getInstance();
        fb_auth = FirebaseAuth.getInstance();

        progressBar.setVisibility(View.INVISIBLE);

        telefon_no_ile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_page.this, With_Phone_Number.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });

        forgatmypass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_page.this, Forgat_pass.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String usernameStr = emailet.getText().toString();
                String passwordStr = passwordet.getText().toString();
                if (!usernameStr.equals("")) {
                    singin(usernameStr, passwordStr);

                } else {

                    Toast.makeText(getApplicationContext(), "lütfen geçerli bir e-posta giriniz", Toast.LENGTH_LONG).show();

                }

            }
        });


        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.login_button_fb);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                Toast.makeText(getApplicationContext(), "giriş başarılı", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login_page.this, MainActivity.class);
                startActivity(intent);
                //handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                Toast.makeText(getApplicationContext(), "iptal edildi", Toast.LENGTH_LONG).show();
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                Toast.makeText(getApplicationContext(), "bir hata oluştu", Toast.LENGTH_LONG).show();
                // ...
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void withgoogle(View view) {

        Intent intent = new Intent(Login_page.this, With_google_sing_up.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);


    }

    private void singin(String username, String password) {


        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Intent intent = new Intent(Login_page.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "telefon numarası veya şifre hatalı", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });


    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            Intent intent = new Intent(Login_page.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}