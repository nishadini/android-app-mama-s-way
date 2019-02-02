package com.example.sitharanishadi.mamasway;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Adding_recipes extends AppCompatActivity {

 private DatabaseReference databaseReference;
    DatabaseReference newrecipe;
 //private StorageReference storageReference;
    private static final int PICK_IMAGE= 100;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_recipes);

        Button addbutton = (Button) findViewById(R.id.addButton);
        final EditText editText = (EditText) findViewById(R.id.editText);//we identify the widgets by id
        final EditText editTextRec = (EditText) findViewById(R.id.editTextRec);
        final EditText editTextCountry= (EditText) findViewById(R.id.editTextCountry);
        final EditText editTextMethod = (EditText) findViewById(R.id.edittextmethod);
        Button addimagebutton = (Button) findViewById(R.id.addimagebutton);


        databaseReference = FirebaseDatabase.getInstance().getReference();//connect to db
        final DatabaseReference recipeChild = databaseReference.child("recipe"); // making a child



        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name =  editText.getText().toString();
                String ingredients = editTextRec.getText().toString();// take the values
                //String method = editTextMethod.getTe.toString();
                String country = editTextCountry.getText().toString();
                String method = editTextMethod.getText().toString();

              newrecipe = recipeChild.push();// push them to db
                 String key = newrecipe.getKey();// we created another child to have the key
              newrecipe.child("name").setValue(name);
              newrecipe.child("ingredients").setValue(ingredients);
              newrecipe.child("Country").setValue(country);
              newrecipe.child("method").setValue(method);

              DatabaseReference nameAndkey = databaseReference.child("nameAndKey");
              nameAndkey.child(name).setValue(key);

                Intent intent2 = new Intent(Adding_recipes.this,search.class);
                startActivity(intent2);







            }
        });

        addimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        if(resultCode== RESULT_OK && requestCode==PICK_IMAGE){
            imageUri= data.getData();
            downloadlink();
        }

    }

    protected void downloadlink(){
        final StorageReference ref = FirebaseStorage.getInstance().getReference().child(" images ");
        UploadTask uploadTask = ref.putFile(imageUri);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult(); // here we have the download link
                    newrecipe.child("imagerec").setValue(downloadUri);// here it will save the download uri in the firebase db

                } else {
                    // Handle failures
                    // ...
                }
            }
        });
    }

}

