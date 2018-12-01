package com.pcadaniak.onetouchguesspokemon;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart,btnOptions,btnAbout,btnScore,btnRate,btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        btnOptions = (Button)findViewById(R.id.btnOptions);
        btnOptions.setOnClickListener(this);
        btnAbout = (Button)findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(this);
        btnScore = (Button)findViewById(R.id.btnScore);
        btnScore.setOnClickListener(this);
        btnRate = (Button)findViewById(R.id.btnRate);
        btnRate.setOnClickListener(this);
        btnExit = (Button)findViewById(R.id.btnExit);
        btnExit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                this.startActivity(new Intent(this, GuessPokemon.class));
                break;
            case R.id.btnOptions:
                this.startActivity(new Intent(this, OptionsActivity.class));
                break;
            case R.id.btnAbout:
                this.startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.btnScore:
                this.startActivity(new Intent(this, ScoreActivity.class));
                break;
            case R.id.btnRate:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.pcadaniak.onetouchguesspokemon"));
                startActivity(intent);
                break;
            case R.id.btnExit:
                finish();
                break;
        }
    }
}
