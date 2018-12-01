package com.pcadaniak.onetouchguesspokemon;

/**
 * Created by PC on 01.05.2017.
 */
public class Models {
    public static class Pokemon
    {
        public int ImgId;
        public String PokemonName;

        public void setImgId(int imgId) {
            this.ImgId = imgId;
        }

        public void setPokemonName(String pokemonName) {
            this.PokemonName = pokemonName;
        }
    }
}
