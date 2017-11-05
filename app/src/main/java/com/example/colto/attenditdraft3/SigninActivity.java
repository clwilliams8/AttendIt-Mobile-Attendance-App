package com.example.colto.attenditdraft3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Firebase stuff
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.colto.attenditdraft3.Model.User;

public class SigninActivity extends AppCompatActivity {

    //Firebase
        FirebaseDatabase database;
        DatabaseReference users;

        EditText editUsername, editPassword;
        Button logOnButton, createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        logOnButton = (Button) findViewById(R.id.logOnButton);
        createAccountButton = (Button) findViewById(R.id.createAccountButton);

        logOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(editUsername.getText().toString(),
                        editPassword.getText().toString());
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccount = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(createAccount);
            }
        });
    }

    private void signIn(final String username, final String password) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(username).exists()) {
                    if (!username.isEmpty()) {
                        User login = dataSnapshot.child(username).getValue(User.class);
                        if (login.getPassword().equals(password) && login.getTeacher().equals(true)) {
                            Toast.makeText(SigninActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                            Intent home = new Intent(getApplicationContext(), InstructorActivity.class);
                            startActivity(home);
                        }
                        if (login.getPassword().equals(password) && login.getTeacher().equals(false)) {
                            Toast.makeText(SigninActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                            Intent studentHome = new Intent(getApplicationContext(), StudentActivity.class);
                            startActivity(studentHome);
                        }
                        if (!login.getPassword().equals(password)) {
                            Toast.makeText(SigninActivity.this, "Password is Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if (!dataSnapshot.child(username).exists()) {
                    Toast.makeText(SigninActivity.this, "Username is Not Registered", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
