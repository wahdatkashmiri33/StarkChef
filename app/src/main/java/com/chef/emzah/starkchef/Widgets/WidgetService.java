package com.chef.emzah.starkchef.Widgets;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.chef.emzah.starkchef.ModalClasses.Recipe;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        //we need to return remote view factory
        return new WidgetDataProvider(this,intent);
    }


}
