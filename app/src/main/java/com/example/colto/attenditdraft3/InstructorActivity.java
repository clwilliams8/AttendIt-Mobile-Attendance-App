package com.example.colto.attenditdraft3;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class InstructorActivity extends AppCompatActivity {

   // private DrawerLayout myDrawer;
    //private ActionBarDrawerToggle myToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        //myDrawer = findViewById(R.id.myDrawer);
       //myToggle = new ActionBarDrawerToggle(this, myDrawer, R.string.open, R.string.close);

        //myDrawer.addDrawerListener(myToggle);
        //myToggle.syncState();

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(myToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
