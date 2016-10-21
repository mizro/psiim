package com.example.tomek.psiim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tomek.psiim.adapters.AnimalAdapter;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private Button buttonAddDog;

    private RecyclerView rvAnimals;
    private LinearLayoutManager layoutManager;
    private AnimalAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        renderView();

        Firebase.setAndroidContext(this);

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
        buttonAddDog = (Button) findViewById(R.id.buttonAddDog);

        buttonAddDog.setOnClickListener(this);

        Firebase ref = new Firebase("https://psiim-c16ef.firebaseio.com");

        Query r = ref.child("Dogs").orderByChild("owner").equalTo(user.getUid());

        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Dog> dogs = new ArrayList<>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Dog dog = dataSnapshot1.getValue(Dog.class);
                    dogs.add(dog);

                }
                adapter.setAnimals(dogs);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        }

    private void renderView() {
        rvAnimals = (RecyclerView) findViewById(R.id.rv_animals);

        layoutManager = new LinearLayoutManager(this);
        rvAnimals.setLayoutManager(layoutManager);

        adapter = new AnimalAdapter();
        rvAnimals.setAdapter(adapter);

        // Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider);
        // RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
        //  rvAnimals.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        //if logout is pressed
        firebaseAuth.signOut();
        //closing activity
        finish();
        //starting login activity
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onClick(View view) {

        if(view == buttonAddDog){

            startActivity(new Intent(this, AddAnimal.class));
        }
    }
}
