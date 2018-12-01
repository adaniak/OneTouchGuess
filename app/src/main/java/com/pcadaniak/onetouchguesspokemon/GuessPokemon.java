package com.pcadaniak.onetouchguesspokemon;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import android.content.SharedPreferences;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GuessPokemon extends AppCompatActivity implements OnClickListener {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public int optLevel, optCountDown, highScore;
    public int countDownMillisecond = 1000;
    TextView txtScore, txtRemainingTime;
    Button leftBtn, rightBtn, btnStart;
    PokeCountDownTimer pokeCountDownTimer;
    ProgressBar progresRemaining;
    String leftText = "", rightText = "";
    int leftId, rightId;
    ArrayList<Models.Pokemon> PokeList;
    public int imgId, notImgId, chooseBtnId, score;
    boolean isOk = false, isChanged = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_pokemon);
        GetOptions();
        StartGame();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        if (pokeCountDownTimer != null && isChanged) {
            progresRemaining.setProgress(progresRemaining.getMax());
            pokeCountDownTimer.cancel();
            isChanged = false;
        }
        switch (v.getId()) {
            case R.id.Left:
                if (imgId == leftId) {
                    isChanged = true;
                    score = score + 1;
                    DefaultLogic();
                    ChangeMillisecond();
                    pokeCountDownTimer = new PokeCountDownTimer(countDownMillisecond, 1);
                    pokeCountDownTimer.start();
                } else {
                    if (score == 0)
                        FirstFinish();
                    else
                        pokeCountDownTimer.onFinish();
                }
                break;
            case R.id.Right:
                if (imgId == rightId) {
                    isChanged = true;
                    score = score + 1;
                    DefaultLogic();
                    ChangeMillisecond();
                    pokeCountDownTimer = new PokeCountDownTimer(countDownMillisecond, 1);
                    pokeCountDownTimer.start();
                } else {
                    if (score == 0)
                        FirstFinish();
                    else
                        pokeCountDownTimer.onFinish();
                }
                break;
        }
    }

    public void StartGame() {
        txtScore = (TextView) findViewById(R.id.txtScore);
        txtRemainingTime = (TextView) findViewById(R.id.txtRemainingTime);
        progresRemaining = (ProgressBar) findViewById(R.id.progressBar);
        try {
            PokeList = LoadPokeList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DefaultLogic();
    }

    public void DefaultLogic() {
        isChanged = true;
        txtScore.setText(Integer.toString(score));
        imgId =NewFunc.GenerateRandomNumber(optLevel); //Functions.GenerateRandomNumber(412, 1);
        notImgId = NewFunc.GenerateRandomNumber(optLevel);//Functions.GenerateRandomNumber(412, 1);
        chooseBtnId = Functions.GenerateRandomNumber(5, 1);
        //begin old link show
        //new Functions.DownloadImageTask((ImageView) findViewById(R.id.imgPokemon))
        //       .execute(Functions.GetRandomPokemonImgLink(imgId));
        //end old link show

        //begin new
        ImageView pokeImg = (ImageView) findViewById(R.id.imgPokemon);
        String imgName = NewFunc.GetRandomPokemonImg(imgId);
        int id = getResources().getIdentifier(imgName, "drawable", getPackageName());
        pokeImg.setImageResource(id);

        //end new
        if (chooseBtnId == 1 || chooseBtnId == 3) {
            leftBtn = (Button) findViewById(R.id.Left);
            leftText = Functions.GetPokemonNameById(PokeList, imgId);
            leftId = imgId;
            leftBtn.setText(leftText);
            rightBtn = (Button) findViewById(R.id.Right);
            rightText = Functions.GetPokemonNameById(PokeList, notImgId);
            rightId = notImgId;
            rightBtn.setText(rightText);
        } else {
            leftBtn = (Button) findViewById(R.id.Left);
            leftText = Functions.GetPokemonNameById(PokeList, notImgId);
            leftId = notImgId;
            leftBtn.setText(leftText);
            rightBtn = (Button) findViewById(R.id.Right);
            rightText = Functions.GetPokemonNameById(PokeList, imgId);
            rightId = imgId;
            rightBtn.setText(rightText);
        }
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.fullscreen_content_controls) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GuessPokemon Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.pcadaniak.onetouchguesspokemon/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GuessPokemon Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.pcadaniak.onetouchguesspokemon/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public class PokeCountDownTimer extends CountDownTimer {

        public PokeCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            int progress = (int) (millisUntilFinished / 1000);
            progresRemaining.setProgress((int) millisUntilFinished);
            //ProgressBarDraw((int)millisUntilFinished,(int)progress*1000-1000);
            //System.out.println("süleyman-onTick-" + Integer.toString(imgId)+ "-" + Long.toString(millisUntilFinished));
        }

        @Override
        public void onFinish() {
            progresRemaining.setProgress(0);
            Toast.makeText(GuessPokemon.this, "Game Over. Your score is " + score, Toast.LENGTH_SHORT).show();
            leftBtn.setEnabled(false);
            rightBtn.setEnabled(false);
            SaveHighScore();
            cancel();
        }
    }

    public ArrayList<Models.Pokemon> LoadPokeList() throws IOException {
        return Functions.LoadJSONFromAsset(getAssets().open("pokemons.json"));
    }

    public void FirstFinish() {
        progresRemaining.setProgress(0);
        Toast.makeText(this, "You are unlucky,try again!", Toast.LENGTH_SHORT).show();
        leftBtn.setEnabled(false);
        rightBtn.setEnabled(false);
    }

    public void GetOptions() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        optLevel = prefs.getInt("Optionlevel", 0);
        optCountDown = prefs.getInt("OptionCountDown", 0);
        if (optCountDown == 2)
            countDownMillisecond = 3000;
        Toast.makeText(this, "Level:" + Integer.toString(optLevel) + " CountDown:" + Integer.toString(optCountDown), Toast.LENGTH_SHORT).show();
        //String restoredText = prefs.getString("OptionLevel", null);
        //if (restoredText != null) {
        //    String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
        //    int idName = prefs.getInt("idName", 0); //0 is the default value.
        //}
    }

    public void ChangeMillisecond() {
        if (optCountDown == 2) {
            if (score < 51)
                countDownMillisecond = 3000;
            else if (score > 50 && score < 101)
                countDownMillisecond = 2000;
            else
                countDownMillisecond = 1000;
        }
    }

    public void SaveHighScore() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        highScore = prefs.getInt("HighScore", 0);
        if (score > highScore) {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("HighScore", score);
            editor.commit();
            Toast.makeText(this, "Best score :) " + Integer.toString(score), Toast.LENGTH_SHORT).show();
        }
    }
}
