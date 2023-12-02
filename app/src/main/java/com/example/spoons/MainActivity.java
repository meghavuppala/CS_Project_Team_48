package com.example.spoons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton tutorial;
    ImageButton clickToPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tutorial = (ImageButton)findViewById(R.id.imageButton7);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTutorial = new Intent(MainActivity.this, TutorialActivity.class);
                startActivity(intentTutorial);
            }
        });

        clickToPlay = (ImageButton)findViewById(R.id.imageButton6);
        clickToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTutorial = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intentTutorial);
            }
        });
    }
}