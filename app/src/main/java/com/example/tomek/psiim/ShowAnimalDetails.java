package com.example.tomek.psiim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.tomek.psiim.adapters.VaccinesAdapter;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ShowAnimalDetails extends AppCompatActivity {

    public static final String ANIMAL_NAME = "animal_name";

    private TextView text;

    private FirebaseAuth firebaseAuth;

    private Button addVaccineButton;

    private RecyclerView rvVaccines;
    private LinearLayoutManager layoutManager;
    private VaccinesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animal_details);
        renderView();

        text = (TextView) findViewById(R.id.textViewName);

        addVaccineButton = (Button) findViewById(R.id.addVaccineButton);

        Firebase.setAndroidContext(this);

        final  Firebase ref = new Firebase("https://psiim-c16ef.firebaseio.com");

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        String u = user.getUid();

        Query r = ref.child("Vaccines").orderByChild("ownerid").equalTo(u);

        Intent intent = getIntent();

        final Bundle b = intent.getExtras();

        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Vaccine> vaccines = new ArrayList<>();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Vaccine vaccine = dataSnapshot1.getValue(Vaccine.class);

                    if (b != null) {
                        String s = (String) b.get(ANIMAL_NAME);
                        text.setText(s);

                        if(vaccine.getDogName().equals(s)){
                            vaccines.add(vaccine);
                        }
                    }
                }
                adapter.setVaccines(vaccines);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void renderView() {
        rvVaccines = (RecyclerView) findViewById(R.id.rv_vaccines);

        layoutManager = new LinearLayoutManager(this);
        rvVaccines.setLayoutManager(layoutManager);

        adapter = new VaccinesAdapter();
        rvVaccines.setAdapter(adapter);

    }

}

