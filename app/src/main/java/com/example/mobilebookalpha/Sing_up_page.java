package com.example.mobilebookalpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Sing_up_page extends AppCompatActivity {
    private EditText gelenkodet, telefonnumberet;
    private Button koduiste, dogrula, kodualamadım;
    private String codesend;
    private TextView waittw;
    private ProgressBar progressBar1;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_page);

        Toast.makeText(getApplicationContext(), "Tek kullanımlık kodu kullanarak ile telefon numaranız ile herzaman giriş yapabilirsiniz şifre oluşturmanız gerekmez", Toast.LENGTH_LONG).show();

        progressBar1.findViewById(R.id.alimeysdfsdfsdfsdf);
        waittw = findViewById(R.id.waittw);
        kodualamadım = findViewById(R.id.kodualamadım);
        gelenkodet = findViewById(R.id.turncodeet);
        telefonnumberet = findViewById(R.id.editTextPhone);
        koduiste = findViewById(R.id.koduistebtn);
        dogrula = findViewById(R.id.dogrula);

        progressBar1.setVisibility(View.INVISIBLE);
        waittw.setVisibility(View.INVISIBLE);
        gelenkodet.setVisibility(View.INVISIBLE);
        dogrula.setVisibility(View.INVISIBLE);
        kodualamadım.setVisibility(View.INVISIBLE);

    }

    private void singWithphonecode() {
        String userEnterCode = gelenkodet.getText().toString();
        if (userEnterCode.equals("")) {
            Toast.makeText(getApplicationContext(), "Lütfen geçerli bir kod giriniz", Toast.LENGTH_LONG).show();
        } else {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codesend, userEnterCode);
            singinWithPhoneAuthCredential(credential);
        }
    }

    private void singinWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar1.setVisibility(View.VISIBLE);
                            Intent i = new Intent(Sing_up_page.this, Login_page.class);
                            startActivity(i);
                            finish();
                            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);

                        } else {
                            Toast.makeText(getApplicationContext(), "girdiğiniz kod hatalı", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {

                }

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codesend = s;
                }
            };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.turn_anim_in, R.anim.turn_anim_out);
    }


    public void koduistecl(View view) {
        String userphonenumber = telefonnumberet.getText().toString();
        if (userphonenumber.equals("")) {
            Toast.makeText(getApplicationContext(), "Lütfen geçerli bir telefon numarası giriniz", Toast.LENGTH_LONG).show();
        } else {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    userphonenumber, 120, TimeUnit.SECONDS,
                    Sing_up_page.this, mCallbacks);

            waittw.setVisibility(View.VISIBLE);
            telefonnumberet.setVisibility(View.INVISIBLE);
            koduiste.setVisibility(View.INVISIBLE);
            gelenkodet.setVisibility(View.VISIBLE);
            dogrula.setVisibility(View.VISIBLE);
            kodualamadım.setVisibility(View.VISIBLE);
            new CountDownTimer(120000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    waittw.setText(" kodun geçerlilik süresi: " + millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                }
            }.start();
        }

    }
   protected void dogrulacl(View view){
       singWithphonecode();

   }
}