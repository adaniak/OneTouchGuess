package com.pcadaniak.onetouchguesspokemon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

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
public class Functions {
    public static int GenerateRandomNumber(int max, int min) {
        Random r = new Random();
        int i1 = r.nextInt(max - min) + min;
        return i1;
    }

    public static String GetRandomPokemonImgLink(int imgId) {
        String index = "";
        if (imgId < 10)
            index = "00" + Integer.toString(imgId);
        else if (imgId < 100)
            index = "0" + Integer.toString(imgId);
        else
            index = Integer.toString(imgId);
        return "https://raw.githubusercontent.com/adaniak/ImageList/master/" + index + ".png";
        //return "@drawable/p" + index +".png";
    }

    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public static ArrayList<Models.Pokemon> LoadJSONFromAsset(InputStream is) {
        ArrayList<Models.Pokemon> locList = new ArrayList<>();
        String json = null;
        try {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("pokemons");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Models.Pokemon pokemon = new Models.Pokemon();
                pokemon.setImgId(i + 1);
                pokemon.setPokemonName((String) jo_inside.getString("Name"));
                locList.add(pokemon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locList;
    }

    public static String GetPokemonNameById(ArrayList<Models.Pokemon> pokeList, int pokeId) {
        String name = "";
        for (Models.Pokemon pokemon : pokeList) {
            if (pokemon.ImgId == pokeId) {
                name = pokemon.PokemonName;
                return name;
            }
        }
        return name;
    }

    public static String GetRandomPokemonImg(int imgId) {
        String index = "";
        if (imgId < 10)
            index = "00" + Integer.toString(imgId);
        else if (imgId < 100)
            index = "0" + Integer.toString(imgId);
        else
            index = Integer.toString(imgId);
        return "https://raw.githubusercontent.com/adaniak/ImageList/master/" + index + ".png";
        //return "@drawable/p" + index +".png";
    }

    public static class DisplayImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DisplayImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
