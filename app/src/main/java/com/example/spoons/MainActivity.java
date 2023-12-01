package com.example.spoons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonPressed;
    Button clickToPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPressed = (Button)findViewById(R.id.button2);
        buttonPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTutorial = new Intent(MainActivity.this, TutorialActivity.class);
                startActivity(intentTutorial);
            }
        });

        clickToPlay = (Button)findViewById(R.id.button);
        clickToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTutorial = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intentTutorial);
            }
        });
    }
}