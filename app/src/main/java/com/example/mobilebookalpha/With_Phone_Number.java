package com.example.mobilebookalpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class With_Phone_Number extends AppCompatActivity {
    private Button kodualamadim, koduiste, dogrula;
    private EditText telefonEt, codeEt;
    private ProgressBar w_progress;
    private TextView gerisayim;
    private String codesendStr;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with__phone__number);
        kodualamadim = findViewById(R.id.W_kodualamadim);
        koduiste = findViewById(R.id.W_koduiste);
        dogrula = findViewById(R.id.W_dogrula);
        gerisayim = findViewById(R.id.gerisayimtw);
        telefonEt = findViewById(R.id.W_phoneEt);
        codeEt = findViewById(R.id.W_codeEt);
        w_progress = findViewById(R.id.W_progres);

        //visibility
        gerisayim.setVisibility(View.INVISIBLE);
        w_progress.setVisibility(View.INVISIBLE);
        kodualamadim.setVisibility(View.INVISIBLE);
        dogrula.setVisibility(View.INVISIBLE);
        codeEt.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(With_Phone_Number.this);
        builder.setTitle("Mobildefter");
        builder.setMessage("Telefon numarası ile giriş tek kullanımlık kod ile yapılır. Çıkış yapmamaya özen gösteriniz.çıkış yaptıktan sonra sonra kod alamazsanız uygulamayı silip tekrar yükleyeniz. Bİlgileriniz telefon numaranızda saklanacaktır");
        builder.setPositiveButton("okudum anladım", null);
        builder.show();


    }


    public void W_kodualamadimcl(View view) {
        kodualamadim.setVisibility(View.INVISIBLE);
        dogrula.setVisibility(View.INVISIBLE);
        codeEt.setVisibility(View.INVISIBLE);
        gerisayim.setVisibility(View.INVISIBLE);
        telefonEt.setVisibility(View.VISIBLE);
        koduiste.setVisibility(View.VISIBLE);
    }

    public void W_koduistecl(View view) {

        String telefonStr = "+9"+telefonEt.getText().toString();

        if (!telefonStr.equals("")) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    telefonStr, 120, TimeUnit.SECONDS, With_Phone_Number.this, mcallbacks);
            koduiste.setVisibility(View.INVISIBLE);
            telefonEt.setVisibility(View.INVISIBLE);
            dogrula.setVisibility(View.VISIBLE);
            codeEt.setVisibility(View.VISIBLE);
            kodualamadim.setVisibility(View.VISIBLE);
            gerisayim.setVisibility(View.VISIBLE);
            new CountDownTimer(120000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    gerisayim.setText(" kodun geçerlilik süresi: " + millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                }
            }.start();
        } else {
            Toast.makeText(this, "bu alan boş geçilemez", Toast.LENGTH_LONG).show();
        }


    }

    public void W_dogrulacl(View view) {

        SingWithphonecode();
    }

    public void SingWithphonecode() {
        String userentercode = codeEt.getText().toString();
        if (!userentercode.equals("")) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codesendStr, userentercode);
            singingwithcreditinal(credential);
        } else {
            Toast.makeText(this, "bu alan boş geçilemez", Toast.LENGTH_LONG).show();
        }

    }

    public void singingwithcreditinal(PhoneAuthCredential credential) {

        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Giriş başarılı ", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(With_Phone_Number.this, MainActivity.class);
                            startActivity(i);
                            finish();
                            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                        } else {
                            Toast.makeText(getApplicationContext(), "Beklenmeyen bir hata oluştu", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks =
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
                    codesendStr = s;
                }
            };
}