package com.chef.emzah.starkchef;

import android.os.Handler;
import android.os.Looper;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.chef.emzah.starkchef.Adapters.RecipeAdapter;
import com.chef.emzah.starkchef.UI.Activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;


//anno runwith let android studio know that we want to run this test with androidjunit4
//androidjunit4 is a class which will help us control launching the app,as well as runnin g test on it
@RunWith(AndroidJUnit4.class)
public class MainActivityBasicTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule=
            new ActivityTestRule<>(MainActivity.class);
    //rule activitytestrule provides functional testing fr a specific activity


    @Test
    public void clickRecyclerViewItemOpens_RecipeActivity(){
//       onView(withId(R.id.recyclerviewrecipe))
//               .perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));
//        onView(withId(R.id.recipe_name)).check(matches(withText("Yellow Cake")));

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        onData(anything()).inAdapterView(withId(R.id.recyclerviewrecipe)).atPosition(0).perform(click());
                        onView(withId(R.id.recipe_name)).check(matches(withText("Nutella Pie")));
                    }
                }, 10000);
            }
        }).run();
    }

}
