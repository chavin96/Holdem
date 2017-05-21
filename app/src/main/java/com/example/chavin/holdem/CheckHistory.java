package com.example.chavin.holdem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckHistory extends AppCompatActivity {

    private TextView mAlarmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_history);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Tomato");
        //hello merge
        mAlarmView = (TextView) findViewById(R.id.alarm_view);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String alarm = dataSnapshot.getValue().toString();
                mAlarmView.setText("Alarm time : " + alarm);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //write
            }
        });
    }
}
