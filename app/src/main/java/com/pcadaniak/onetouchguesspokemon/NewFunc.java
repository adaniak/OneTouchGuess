package com.pcadaniak.onetouchguesspokemon;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by PC on 01.05.2017.
 */
public class NewFunc {
    public static int GenerateRandomNumber(int level) {
        int min = 1, max = 1;
        if (level == 1) {
            min = 1;
            max = 151;
        } else if (level == 2) {
            min = 152;
            max = 251;
        } else if (level == 3) {
            min = 252;
            max = 384;
        } else if (level == 4) {
            min = 387;
            max = 488;
        } else if (level == 5) {
            min = 1;
            max = 488;
        }
        Random r = new Random();
        int i1 = r.nextInt(max - min) + min;
        return i1;
    }

    public static String GetRandomPokemonImg(int imgId) {
        String index = "";
        if (imgId < 10)
            index = "00" + Integer.toString(imgId);
        else if (imgId < 100)
            index = "0" + Integer.toString(imgId);
        else
            index = Integer.toString(imgId);
        return "p" + index;
    }

    public static int nextImage(int imgId, Resources res, String packName) {
        String imgName = GetRandomPokemonImg(imgId);
        return res.getIdentifier(imgName, "drawable", packName);
    }

}
