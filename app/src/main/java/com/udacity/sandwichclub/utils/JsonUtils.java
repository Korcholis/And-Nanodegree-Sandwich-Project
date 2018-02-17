package com.udacity.sandwichclub.utils;

import android.util.JsonReader;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONArray ingredientsJson = sandwichJson.getJSONArray("ingredients");
            JSONArray alsoKnownAsJson = sandwichJson.getJSONObject("name").getJSONArray("alsoKnownAs");

            Sandwich sandwich = new Sandwich();
            sandwich.setMainName(sandwichJson.getJSONObject("name").getString("mainName"));
            sandwich.setImage(sandwichJson.getString("image"));
            sandwich.setDescription(sandwichJson.getString("description"));
            sandwich.setPlaceOfOrigin(sandwichJson.getString("placeOfOrigin"));

            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsJson.length(); i++) {
                ingredients.add(ingredientsJson.getString(i));
            }

            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJson.length(); i++) {
                alsoKnownAs.add(alsoKnownAsJson.getString(i));
            }

            sandwich.setIngredients(ingredients);
            sandwich.setAlsoKnownAs(alsoKnownAs);

            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
