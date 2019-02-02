package com.example.sitharanishadi.mamasway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Comment;

public class search extends AppCompatActivity {
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final Button search = (Button) findViewById(R.id.search);
        final Button addrec = (Button) findViewById(R.id.addrec);


        final EditText searchrec= (EditText) findViewById(R.id.searchrec);

        databaseReference = FirebaseDatabase.getInstance().getReference();//connect to db

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                String post = searchrec.getText().toString();
               // String name= dataSnapshot.getValue(String.class);

               Intent intent = new Intent(search.this,View_Recipe.class);
               intent.putExtra("name","val");
               startActivity(intent);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        };

        addrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(search.this,Adding_recipes.class);
                startActivity(intent1);
            }
        });


        databaseReference.addValueEventListener(postListener);

    }

}
