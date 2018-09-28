package com.chef.emzah.starkchef.Widgets;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.chef.emzah.starkchef.Common.Common;
import com.chef.emzah.starkchef.ModalClasses.Ingredient;
import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.R;

import java.util.ArrayList;
import java.util.List;

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
  //we need context var so that widget can get package name with which app widget is asociated with
    Context context;
    Intent intent;
    private Recipe recipe;
    //we are gonna make constructor so that we can access it from service class


    ///now we need to populate data in listitem arraylist

    public WidgetDataProvider(Context context, Intent intent) {
        this.context = context;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        recipe= Common.retrieveWidgetItemFromSharedPreference(context);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (recipe == null) return 0;
        return recipe.getIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
       if (recipe ==null)
           return null;
       Ingredient ingredient=recipe.getIngredients().get(position);
       String content= Common.formatIngrendientText(ingredient,position);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_listitem);

        views.setTextViewText(R.id.text_single_ingredient, content);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
