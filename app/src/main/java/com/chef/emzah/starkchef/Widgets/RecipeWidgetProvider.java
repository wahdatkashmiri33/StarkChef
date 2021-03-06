package com.chef.emzah.starkchef.Widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.chef.emzah.starkchef.Common.Common;
import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.R;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    public static final String INGEREDIENT_TEXT = " Ingredients";
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Recipe recipe=Common.retrieveWidgetItemFromSharedPreference(context);
        CharSequence widgetText=recipe.getName() + INGEREDIENT_TEXT;

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
        views.setTextViewText(R.id.appwidget_text,widgetText);
        Intent intent=new Intent(context,WidgetService.class);
        views.setRemoteAdapter(R.id.widget_listview,intent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        /// There may be multiple widgets active, so update all of them
       updateIngredientWidgets(context,appWidgetManager,appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    public static void updateIngredientWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }
}

