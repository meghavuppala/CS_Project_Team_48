package com.example.spoons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TutorialActivity extends AppCompatActivity {

    ImageButton backButtonPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        backButtonPressed = (ImageButton)findViewById(R.id.imageButton3);
        backButtonPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTutorial = new Intent(TutorialActivity.this, MainActivity.class);
                startActivity(intentTutorial);
            }
        });
    }
}