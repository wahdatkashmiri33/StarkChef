package com.chef.emzah.starkchef.Common;

import android.content.Context;
import android.content.SharedPreferences;

import com.chef.emzah.starkchef.ModalClasses.Ingredient;
import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.google.gson.Gson;

public class Common {
    public static final String BAKING_APP_PREFERENCE = "current_recipe_preference";
    public static final String LAST_RECIPE_KEY = "current_recipe";
    public static Recipe retrieveWidgetItemFromSharedPreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(BAKING_APP_PREFERENCE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LAST_RECIPE_KEY, "");
        return gson.fromJson(json, Recipe.class);
    }

    public static String formatIngrendientText(Ingredient ingredient, int position) {
        return (position+1)
                +". "+ingredient.getQuantity()
                + " " + ingredient.getMeasure()
                + " " + ingredient.getIngredient();
    }
}
