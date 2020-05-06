package com.example.firestore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeworkActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText editTitle, editType;
    private String priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);

        editTitle = findViewById(R.id.editTitle);
        editType = findViewById(R.id.editType);
    }

    @Override
    protected void onStart() {
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }

    public void saveOnFirebase(View view) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",
                new Locale("pt", "BR"));
        String date = df.format(new Date());
        String title = editTitle.getText().toString();
        String type = editType.getText().toString();

        Homework homework = new Homework(title, type, date, this.priority);

        db.collection("homework").add(homework)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String message = "Homework Created!";
                        Toast.makeText(HomeworkActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String message = "Failed to create Homework!";
                        Toast.makeText(HomeworkActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void returnToMainPage(View view) {
        Intent mainPage = new Intent(HomeworkActivity.this, Dashboard.class);
        startActivity(mainPage);
        finish();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioHigh:
                if (checked)
                    priority = "High";
                break;
            case R.id.radioNormal:
                if (checked)
                    priority = "Normal";
                break;
            case R.id.radioLow:
                if (checked)
                    priority = "Low";
                break;
        }
    }
}
