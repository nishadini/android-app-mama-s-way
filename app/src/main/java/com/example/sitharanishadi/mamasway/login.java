package com.example.sitharanishadi.mamasway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //get values from the database which are already in login and when click //create a new account in the same db
        //pass the username to the next activiy as a global variable
    }

    public void create(View v){
        Intent i = new Intent(this,Create_Account.class);
        startActivity(i);

    }
}
