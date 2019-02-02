package com.example.sitharanishadi.mamasway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Create_Account extends AppCompatActivity {

    private DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__account);

        Button submitbutton = (Button) findViewById(R.id.buttonSubmit);
        final EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        final EditText editPassword = (EditText) findViewById(R.id.editPassword);
        final EditText editTextReEnter = (EditText) findViewById(R.id.editTextReEnter);
        final EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        final EditText editTextUCountry = (EditText) findViewById(R.id.editTextUCountry);

        databaseReference1 = FirebaseDatabase.getInstance().getReference();//connect to db
        final DatabaseReference UserAccountChild = databaseReference1.child("UserAccounts"); // making a child

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName= editTextUserName.getText().toString();
                String Password = editPassword.getText().toString();
                String RePassword = editTextReEnter.getText().toString();
                String PhoneNumber = editTextPhone.getText().toString();
                String Country = editTextUCountry.getText().toString();

                DatabaseReference newUserAccount = UserAccountChild.push();
                newUserAccount.child("User Name").setValue(UserName);
                newUserAccount.child("Password").setValue(Password);
                newUserAccount.child("PhoneNumber").setValue(PhoneNumber);
                newUserAccount.child("Country").setValue(Country);
                 //confirm the pass word compare two strings


            }
        });

    }


}
