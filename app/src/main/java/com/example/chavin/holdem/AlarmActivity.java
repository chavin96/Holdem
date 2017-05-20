package com.example.chavin.holdem;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlarmActivity extends AppCompatActivity  {

    //to make the alarm manager
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    Context context;
    PendingIntent pending_intent;
    TextView status;
    protected int hour;
    protected int minute;
    //defining firebase auth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        this.context = this;

        //initializing the alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //inititalizing the time picker
        alarm_timepicker = (TimePicker)findViewById(R.id.timePicker);

        //initialising the status
        status = (TextView)findViewById(R.id.status);

        //create an instance of a calander
        final Calendar calendar = Calendar.getInstance();

        //initialize start button
        Button alarm_on = (Button)findViewById(R.id.buttonNewAlarm);

        final Intent my_intent = new Intent(this.context, Alarm_receiver.class);


        //create an onclick listner
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //setting hours and minutes using old methods
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getCurrentMinute());

                UserModel um = new UserModel();
                //get the int values of hour and minutes
                hour = (alarm_timepicker.getCurrentHour());
                minute = (alarm_timepicker.getCurrentMinute());
                String hour_string = "hh";
                String minute_string = "mm";
                //convert the int values to string
                if (hour < 10 && hour >= 0) {
                    hour_string = "0" + String.valueOf(hour);
                }
                else {
                    hour_string = String.valueOf(hour);
                }
                if(minute<10 && minute >= 0){
                    minute_string = "0" + String.valueOf(minute);
                }
                else {
                    minute_string = String.valueOf(minute);
                }



                //method that changes the status Textbox
                set_alarm_text("Alarm set to:" + hour_string + ":" + minute_string);

                //create a pending intent to delay the intent until the specified calender time
                pending_intent = PendingIntent.getBroadcast(AlarmActivity.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);


                //set the alarm manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pending_intent);

                //set alarm variable
                um.setAlarm(hour_string + ":" + minute_string);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                databaseReference.child(user.getUid()).push().setValue(um);

            }
        });

        //initialize end button
        Button alarm_off = (Button)findViewById(R.id.buttonStopAlarm);
        //create an onclick listner
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //method that changes the status textbox
                set_alarm_text("Alarm off");

                //cancel the alarm
                alarm_manager.cancel(pending_intent);

                //put extra string into my_intent
                //tells the clock that you pressed the "alarm off" button
                my_intent.putExtra("extra","alarm off");

                //stop the ringtone
                sendBroadcast(my_intent);
            }
        });

    }

    private void set_alarm_text(String output) {
        status.setText(output);
    }



}
