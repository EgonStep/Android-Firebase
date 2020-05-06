package com.example.firestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView textWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();
        textWelcome = findViewById(R.id.textWelcome);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        textWelcome.setText("Welcome, " + user.getEmail());
    }

    public void createHomework(View view) {
        Intent homeworkActivity = new Intent(Dashboard.this, HomeworkActivity.class);
        startActivity(homeworkActivity);
        finish();
    }

    public void leave(View view) {
        mAuth.signOut();
        Intent init = new Intent(Dashboard.this, MainActivity.class);
        startActivity(init);
        finish();
    }
}
