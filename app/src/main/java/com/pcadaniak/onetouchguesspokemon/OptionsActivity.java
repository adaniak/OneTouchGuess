package com.pcadaniak.onetouchguesspokemon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class OptionsActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    Button btnSave;
    int choosenLevel,choosenCountDown,currentLevel,currentCountDown;
    RadioButton radioGen1,radioGen2,radioGen3,radioGen4,radioGenAll,radioDescending,radioOneSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        btnSave = (Button)findViewById(R.id.btnOptionSave);
        btnSave.setOnClickListener(this);
        DefaultValues();
    }
    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        //editor.putString("Level",choosenLevel );
        editor.putInt("Optionlevel", choosenLevel);
        editor.putInt("OptionCountDown", choosenCountDown);
        editor.commit();
        this.startActivity(new Intent(this, MainActivity.class));
    }

    public void onRadioButtonLevelClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioGen1:
                if (checked)
                    choosenLevel = 1;
                    break;
            case R.id.radioGen2:
                if (checked)
                    choosenLevel = 2;
                    break;
            case R.id.radioGen3:
                if (checked)
                    choosenLevel = 3;
                    break;
            case R.id.radioGen4:
                if (checked)
                    choosenLevel = 4;
                break;
            case R.id.radioGenAll:
                if (checked)
                    choosenLevel = 5;
                    break;
        }
    }
    public void onRadioButtonCountDownClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioDescending:
                if (checked)
                    choosenCountDown = 1;
                    break;
            case R.id.radioOneSecond:
                if (checked)
                    choosenCountDown = 2;
                    break;
        }
    }
    public void DefaultValues()
    {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        currentLevel = prefs.getInt("Optionlevel", 0);
        choosenLevel = currentLevel;
        currentCountDown = prefs.getInt("OptionCountDown", 0);
        choosenCountDown = currentCountDown;
        radioGen1 = (RadioButton)findViewById(R.id.radioGen1);
        radioGen2 = (RadioButton)findViewById(R.id.radioGen2);
        radioGen3 = (RadioButton)findViewById(R.id.radioGen3);
        radioGen4 = (RadioButton)findViewById(R.id.radioGen4);
        radioGenAll = (RadioButton)findViewById(R.id.radioGenAll);
        radioDescending = (RadioButton)findViewById(R.id.radioDescending);
        radioOneSecond = (RadioButton)findViewById(R.id.radioOneSecond);
        if(currentLevel == 1)
            radioGen1.setChecked(true);
        else if(currentLevel == 2)
            radioGen2.setChecked(true);
        else if(currentLevel == 3)
            radioGen3.setChecked(true);
        else if(currentLevel == 4)
            radioGen4.setChecked(true);
        else if(currentLevel == 5)
            radioGenAll.setChecked(true);
        else
            radioGen1.setChecked(true);

        if(currentCountDown == 1)
            radioDescending.setChecked(true);
        else if(currentCountDown == 2)
            radioOneSecond.setChecked(true);
        else
            radioDescending.setChecked(true);
    }
}
