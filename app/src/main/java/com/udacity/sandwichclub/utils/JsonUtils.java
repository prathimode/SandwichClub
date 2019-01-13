package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    private final static String kJsonNameTag = "name";
    private final static String kJsonMainNameTag = "mainName";
    private final static String kJsonAlsoKnownAsTag = "alsoKnownAs";

    private final static String kJsonPlaceOfOriginTag = "placeOfOrigin";
    private final static String kJsonDescriptionTag = "description";
    private final static String kJsonImageTag = "image";
    private final static String kJsonIngredientsTag = "ingredients";
    private final static String kfallbackString = "Invalid Data";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwichObj = null;
        if (!TextUtils.isEmpty(json)) {
            try {
                sandwichObj = new Sandwich();
                JSONObject sandwichJSONObj = new JSONObject(json);

                JSONObject nameJSONObj = sandwichJSONObj.optJSONObject(kJsonNameTag);
                if(nameJSONObj != null) {
                    sandwichObj.setMainName(nameJSONObj.optString(kJsonMainNameTag, kfallbackString));

                    if (nameJSONObj.has(kJsonAlsoKnownAsTag)) {
                        JSONArray knownAsJSONArray = nameJSONObj.optJSONArray(kJsonAlsoKnownAsTag);
                        if(knownAsJSONArray != null) {
                            ArrayList<String> knowAsList = new ArrayList<>();
                            for (int i = 0; i < knownAsJSONArray.length(); i++) {
                                knowAsList.add(knownAsJSONArray.optString(i,kfallbackString));
                            }
                            sandwichObj.setAlsoKnownAs(knowAsList);
                        }
                    }
                    if (sandwichJSONObj.has(kJsonPlaceOfOriginTag)) {
                        sandwichObj.setPlaceOfOrigin(sandwichJSONObj.getString(kJsonPlaceOfOriginTag));
                    }
                    if (sandwichJSONObj.has(kJsonDescriptionTag)) {
                        sandwichObj.setDescription(sandwichJSONObj.getString(kJsonDescriptionTag));
                    }
                    if (sandwichJSONObj.has(kJsonImageTag)) {
                        sandwichObj.setImage(sandwichJSONObj.getString(kJsonImageTag));
                    }
                    if (sandwichJSONObj.has(kJsonIngredientsTag)) {
                        JSONArray ingredientsJSONArray = sandwichJSONObj.optJSONArray(kJsonIngredientsTag);
                        if(ingredientsJSONArray != null) {
                            ArrayList<String> ingredientsList = new ArrayList<>();
                            for (int i = 0; i < ingredientsJSONArray.length(); i++) {
                                ingredientsList.add(ingredientsJSONArray.optString(i, kfallbackString));
                            }
                            sandwichObj.setIngredients(ingredientsList);
                        }
                    }
                }

            } catch (JSONException e) {
                Log.e(JsonUtils.class.getSimpleName() , "Invalid Sandwich Json : " + e.getMessage());
                return null;
            }
        }
        return sandwichObj;
    }


}
