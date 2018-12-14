package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Optional;

public class JsonUtils {

    private final static String kJsonNameTag = "name";
    private final static String kJsonMainNameTag = "mainName";
    private final static String kJsonAlsoKnownAsTag = "alsoKnownAs";

    private final static String kJsonPlaceOfOriginTag = "placeOfOrigin";
    private final static String kJsonDescriptionTag = "description";
    private final static String kJsonImageTag = "image";
    private final static String kJsonIngredientsTag = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwichObj = null;
        if (!TextUtils.isEmpty(json)) {
            try {
                sandwichObj = new Sandwich();
                JSONObject sandwichJSONObj = new JSONObject(json);

                JSONObject nameJSONObj = sandwichJSONObj.getJSONObject(kJsonNameTag);
                sandwichObj.setMainName(nameJSONObj.getString(kJsonMainNameTag));

                if (nameJSONObj.has(kJsonAlsoKnownAsTag)) {
                    JSONArray knownAsJSONArray = nameJSONObj.getJSONArray(kJsonAlsoKnownAsTag);
                    ArrayList<String> knowAsList =  new ArrayList<>();
                    for (int i = 0 ; i < knownAsJSONArray.length() ; i++) {
                        knowAsList.add(knownAsJSONArray.getString(i));
                    }
                    sandwichObj.setAlsoKnownAs(knowAsList);
                }
                if(sandwichJSONObj.has(kJsonPlaceOfOriginTag)) {
                    sandwichObj.setPlaceOfOrigin(sandwichJSONObj.getString(kJsonPlaceOfOriginTag));
                }
                if(sandwichJSONObj.has(kJsonDescriptionTag)) {
                    sandwichObj.setDescription(sandwichJSONObj.getString(kJsonDescriptionTag));
                }
                if(sandwichJSONObj.has(kJsonImageTag))
                {
                    sandwichObj.setImage(sandwichJSONObj.getString(kJsonImageTag));
                }
                if (nameJSONObj.has(kJsonIngredientsTag)) {
                    JSONArray ingredientsJSONArray = nameJSONObj.getJSONArray(kJsonIngredientsTag);
                    ArrayList<String> ingredientsList =  new ArrayList<>();
                    for (int i = 0 ; i < ingredientsJSONArray.length() ; i++) {
                        ingredientsList.add(ingredientsJSONArray.getString(i));
                    }
                    sandwichObj.setIngredients(ingredientsList);
                }

            } catch (JSONException e) {
                Log.e(JsonUtils.class.getSimpleName() , "Invalid Sandwich Json : " + e.getMessage());
                return null;
            }
        }
        return sandwichObj;
    }


}
