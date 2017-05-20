package com.example.chavin.holdem;

/**
 * Created by Chavin on 4/12/2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button buttonAlarm;
    private Button buttonNewBrush;
    private Button buttonDashboard;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonAlarm = (Button) findViewById(R.id.buttonAlarm);
        buttonDashboard = (Button) findViewById(R.id.buttonDashboard);
        buttonNewBrush= (Button) findViewById(R.id.buttonNewBrush);

        //displaying logged in user name
        textViewUserEmail.setText("Welcome "+user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(this);
        buttonAlarm.setOnClickListener(this);
        buttonDashboard.setOnClickListener(this);
        buttonNewBrush.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        if(view==buttonAlarm){
            //starting alarm
            startActivity(new Intent(this, AlarmActivity.class));
        }

        if(view==buttonDashboard){
            //starting dashboard
            startActivity(new Intent(this, CheckHistory.class));
        }

        if(view==buttonNewBrush){
            //starting new brush
            startActivity(new Intent(this, NewBrush.class));
        }

        //if logout is pressedd
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}