package com.chef.emzah.starkchef;

import android.os.Handler;
import android.os.Looper;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.chef.emzah.starkchef.UI.Activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsAnything.anything;

@RunWith(AndroidJUnit4.class)
public class StepsTestActivity {

    @Rule
    public ActivityTestRule<MainActivity> selectStepActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickRecyclerViewItem_opensViewStepActivity() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Looper.myLooper() == null) {
                    Looper.prepare();
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        onData(anything()).inAdapterView(withId(R.id.noserve)).atPosition(0).perform(click());
                        onData(anything()).inAdapterView(withId(R.id.nosteps)).atPosition(0).perform(click());

                    }
                }, 10000);
            }
        }).run();
    }
}