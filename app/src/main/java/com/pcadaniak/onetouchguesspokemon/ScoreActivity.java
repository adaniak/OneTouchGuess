package com.pcadaniak.onetouchguesspokemon;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int highScore = prefs.getInt("HighScore", 0);
        Toast.makeText(this, "Best score :)" + Integer.toString(highScore), Toast.LENGTH_SHORT).show();
    }
}
