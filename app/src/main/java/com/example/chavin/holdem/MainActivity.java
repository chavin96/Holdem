    package com.example.chavin.holdem;

    import android.app.DatePickerDialog;
    import android.app.Dialog;
    import android.app.FragmentTransaction;
    import android.app.ProgressDialog;
    import android.content.Intent;
    import java.util.Calendar;
    import android.support.annotation.NonNull;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.text.TextUtils;
    import android.view.View;
    import android.widget.Button;
    import android.widget.DatePicker;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;


    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.AuthResult;
    import com.google.firebase.auth.FirebaseAuth;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        //defining view objects
        private EditText editTextEmail;
        private EditText editTextPassword;
        private Button buttonSignup;

        //private Button get_Date_Btn;
        private TextView textViewSignin;
    /*    int year_x,month_x,day_x;
        static final int dialogId=0;*/

        private ProgressDialog progressDialog;


        //defining firebase auth object
        private FirebaseAuth firebaseAuth;


     /*   public void DOB(){
            get_Date_Btn = (Button)findViewById(R.id.get_Date_Btn);
            get_Date_Btn.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDialog(dialogId);
                        }
                    }
            );
        }*/

       /* @Override
        protected Dialog onCreateDialog(int id){
            if(id==dialogId)
                return new DatePickerDialog(this, dpickerListner ,year_x ,month_x ,day_x);
                    return null;
        }

        private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                year_x=year;
                month_x=month+1;
                day_x=dayOfMonth;
                Toast.makeText(MainActivity.this,year_x+"/"+month_x+"/"+day_x,Toast.LENGTH_LONG);
            }
        };*/

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //initializing firebase auth object
            firebaseAuth = FirebaseAuth.getInstance();

            //if getCurrentUser does not returns null
            if (firebaseAuth.getCurrentUser() != null) {
                //that means user is already logged in
                //so close this activity
                finish();

                //and open profile activity
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }

            //initializing views
            editTextEmail = (EditText) findViewById(R.id.editTextEmail);
            editTextPassword = (EditText) findViewById(R.id.editTextPassword);
            textViewSignin = (TextView) findViewById(R.id.textViewSignin);

            buttonSignup = (Button) findViewById(R.id.buttonSignup);

            progressDialog = new ProgressDialog(this);

            //attaching listener to button
            buttonSignup.setOnClickListener(this);
            textViewSignin.setOnClickListener(this);

       /*     final Calendar cal =  Calendar.getInstance();
            year_x=cal.get(Calendar.YEAR);
            month_x=cal.get(Calendar.MONTH);
            day_x=cal.get(Calendar.DATE);*/

        }

        public void onStart(){
            super.onStart();

            EditText txtDate=(EditText)findViewById(R.id.txtdate);
            txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
                public void onFocusChange(View view, boolean hasfocus){
                    if(hasfocus){
                        DateDialog dialog=new DateDialog(view);
                        FragmentTransaction ft =getFragmentManager().beginTransaction();
                        dialog.show(ft, "DatePicker");

                    }
                }

            });
        }

        private void registerUser() {

            //getting email and password from edit texts
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            //checking if email and passwords are empty
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
                return;
            }

            //if the email and password are not empty
            //displaying a progress dialog

            progressDialog.setMessage("Registering Please Wait...");
            progressDialog.show();

            //creating a new user
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if (task.isSuccessful()) {
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            } else {
                                //display some message here
                                Toast.makeText(MainActivity.this, "Registration Error ", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

        }

        @Override
        public void onClick(View view) {

            if (view == buttonSignup) {
                registerUser();
            }

            if (view == textViewSignin) {
                //open login activity when user taps on the already registered textview
                startActivity(new Intent(this, LoginActivity.class));
            }

        }
    }