package com.example.spoons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CreateGameHostActivity extends AppCompatActivity {
    ImageButton backButtonPressed;
    ImageButton tutorialButton;
    ImageButton playButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game_host);

        backButtonPressed = (ImageButton)findViewById(R.id.backButton);
        backButtonPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTutorial = new Intent(CreateGameHostActivity.this, CreateActivity.class);
                startActivity(intentTutorial);
            }
        });

        tutorialButton = (ImageButton)findViewById(R.id.imageButton5);
        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTutorial = new Intent(CreateGameHostActivity.this, TutorialActivity.class);
                startActivity(intentTutorial);
            }
        });

        playButton = (ImageButton)findViewById(R.id.imageButton4);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTutorial = new Intent(CreateGameHostActivity.this, PlayerViewActivity.class);
                startActivity(intentTutorial);
            }
        });
    }
}