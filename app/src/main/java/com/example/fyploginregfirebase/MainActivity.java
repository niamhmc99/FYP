package com.example.fyploginregfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    Button buttonLogut;
    ImageButton imageButtonAnimals, imageButtonExpenditure, imageButtonVets, imageButtonEmployees;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        buttonLogut = findViewById(R.id.buttonLogout);
        buttonLogut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
            }
        });

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (auth.getCurrentUser() == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    protected void onResume() {
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        super.onResume();
    }

    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthStateListener);
    }

    public void onStop() {
        super.onStop();
        auth.addAuthStateListener(mAuthStateListener);
    }

    public void clickAnimals(View view) {
        imageButtonAnimals = (ImageButton) findViewById(R.id.imageButtonAnimals);
        imageButtonAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Animals Registered", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, AnimalActivity.class));

            }
        }
        );
    }

    public void clickExpenses(View view){
        imageButtonExpenditure = (ImageButton) findViewById(R.id.imageButtonExpenses);
        imageButtonExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExpenditureActivity.class));
            }
        });
    }

    public void clickVets (View view){
        imageButtonVets = (ImageButton) findViewById(R.id.imageButtonVets);
        imageButtonVets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VetActivity.class));

            }
        });
    }

    public void clickEmployees(View view) {
        imageButtonEmployees = (ImageButton) findViewById(R.id.imageButtonEmployees);
        imageButtonEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EmployeeActivity.class));
            }
        });
    }
}
