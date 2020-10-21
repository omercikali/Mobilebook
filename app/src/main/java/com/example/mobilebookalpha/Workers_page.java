package com.example.mobilebookalpha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Workers_page extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference mref;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_page);
button=findViewById(R.id.addrecord);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        database = FirebaseDatabase.getInstance();
        mref = database.getReference("kisiler");
        Isciler isci = new Isciler("", "ali", "20");
        Isciler isci2 = new Isciler("", "mehmet", "50");
        Isciler isci3 = new Isciler("", "kerem", "21");
        Isciler isci4 = new Isciler("", "bilal", "40");
        Isciler isci5 = new Isciler("", "hasan", "27");
        Isciler isci6 = new Isciler("", "muzaffer", "30");
        Isciler isci7 = new Isciler("", "talip", "25");
        mref.push().setValue(isci);
        mref.push().setValue(isci2);
        mref.push().setValue(isci3);
        mref.push().setValue(isci4);
        mref.push().setValue(isci5);
        mref.push().setValue(isci6);
        mref.push().setValue(isci7);
    }
});



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.turn_anim_in, R.anim.turn_anim_out);
    }
}