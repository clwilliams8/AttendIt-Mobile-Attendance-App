package com.example.colto.attenditdraft3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

//firebase stuff
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.example.colto.attenditdraft3.Model.User;

public class MainActivity extends AppCompatActivity {

    //Firebase
    FirebaseDatabase database;
    DatabaseReference users;

    EditText editUsername, editPassword, editEmail;
    CheckBox instructorCheckbox;
    Button registerButton, signinButton, logOnButton, createAccountButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        //EditText,Buttons
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editEmail = (EditText) findViewById(R.id.editEmail);
        instructorCheckbox = (CheckBox) findViewById(R.id.instructorCheckbox);

        registerButton = (Button) findViewById(R.id.registerButton);
        signinButton = (Button) findViewById(R.id.signinButton);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(signIn);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User(editUsername.getText().toString(),
                        editPassword.getText().toString(),
                        editEmail.getText().toString(),
                        instructorCheckbox.isChecked());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getUsername()).exists())
                            Toast.makeText(MainActivity.this, "The Username Already Exsist!", Toast.LENGTH_SHORT).show();
                        else {
                            users.child(user.getUsername()).setValue(user);
                            Toast.makeText(MainActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
