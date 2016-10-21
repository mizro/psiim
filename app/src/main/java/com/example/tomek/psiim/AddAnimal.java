package com.example.tomek.psiim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddAnimal extends AppCompatActivity {

    private Button buttonInsertDog;
    private EditText editTextDogName;
    private EditText editTextBirthDate;
    private TextView textViewDogs;
    private EditText editTextColor;
    private EditText editTextSpecialSigns;
    private EditText editTextUniqNumber;
    private EditText editTextRace;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        Firebase.setAndroidContext(this);

        buttonInsertDog = (Button) findViewById(R.id.buttonInsertDog);
        editTextBirthDate = (EditText) findViewById(R.id.editTextBirthDate);
        editTextDogName = (EditText) findViewById(R.id.editTextDogName);
        textViewDogs = (TextView) findViewById(R.id.textViewDogs);
        editTextUniqNumber = (EditText) findViewById(R.id.editTextUniqNumber);
        editTextRace = (EditText) findViewById(R.id.editTextRace);
        editTextColor = (EditText) findViewById(R.id.editTextColor);
        editTextSpecialSigns = (EditText) findViewById(R.id.editTextSpecialSigns);


        buttonInsertDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Firebase ref = new Firebase("https://psiim-c16ef.firebaseio.com");

                firebaseAuth = FirebaseAuth.getInstance();

                FirebaseUser user = firebaseAuth.getCurrentUser();
                String u = user.getUid();

                String name = editTextDogName.getText().toString().trim();
                String birthdate = editTextBirthDate.getText().toString().trim();
                String userID = u;
                String color = editTextColor.getText().toString().trim();
                String specialSigns = editTextSpecialSigns.getText().toString().trim();
                String uniqNumber = editTextUniqNumber.getText().toString().trim();
                String race = editTextRace.getText().toString().trim();

                Dog dog = new Dog();

                dog.setBirthdate(birthdate);
                dog.setName(name);
                dog.setOwner(userID);
                dog.setColor(color);
                dog.setRace(race);
                dog.setSpecialSigns(specialSigns);
                dog.setUniqNumber(uniqNumber);

                Firebase newref = ref.child("Dogs").push();
                newref.setValue(dog);

                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);

            }
        });



    }
}
