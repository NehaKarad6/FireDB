package com.example.neha.firedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String firstname;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.btnsubmit).setOnClickListener(this::Click);
        findViewById(R.id.btnsubmit).setOnClickListener(this::Display);
    }

    private void Click(View view) {
        firstname=((EditText) findViewById(R.id.edtname)).getText().toString();
        email=((EditText) findViewById(R.id.edtemail)).getText().toString();
        User user = new User(firstname, email);

        mDatabase.child("users").child(firstname).setValue(user);

    }

    public void Display(View view) {
        mDatabase.child("users").child("8390").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                Log.i("@codekul", ""+user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}