package com.example.spoons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CreateActivity extends AppCompatActivity {
    ImageButton backButtonPressed;
    ImageButton tutorialButton;
    ImageButton createGameButton;

    ImageButton discoverGameButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        backButtonPressed = (ImageButton)findViewById(R.id.backButton);
        backButtonPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTutorial = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intentTutorial);
            }
        });

        tutorialButton = (ImageButton)findViewById(R.id.imageButton);
        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTutorial = new Intent(CreateActivity.this, TutorialActivity.class);
                startActivity(intentTutorial);
            }
        });

        createGameButton = (ImageButton)findViewById(R.id.imageView2);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTutorial = new Intent(CreateActivity.this,DiscoverRoomsActivity.class);
                startActivity(intentTutorial);
            }
        });

        discoverGameButton = (ImageButton)findViewById(R.id.imageView3);
        discoverGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTutorial = new Intent(CreateActivity.this, MainActivity2.class);
                startActivity(intentTutorial);
            }
        });
    }
}