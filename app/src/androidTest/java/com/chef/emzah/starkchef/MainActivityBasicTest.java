package com.chef.emzah.starkchef;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.chef.emzah.starkchef.Adapters.RecipeAdapter;
import com.chef.emzah.starkchef.UI.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

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
       onView(ViewMatchers.withId(R.id.recyclerviewrecipe))
               .perform(RecyclerViewActions.<RecipeAdapter.RecipeViewHolder>actionOnItemAtPosition(2,click()));
    }
}
