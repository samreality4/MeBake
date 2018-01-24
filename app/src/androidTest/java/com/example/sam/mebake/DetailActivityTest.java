package com.example.sam.mebake;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Sam on 1/24/2018.
 */

    @LargeTest
    @RunWith(AndroidJUnit4.class)
    public class DetailActivityTest {

        CountingIdlingResource countingIdlingResource;

        @Rule
        public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

        @Before
        public void addIdlingResource() {
            countingIdlingResource  = mActivityTestRule.getActivity().countingIdlingResource;
            IdlingRegistry.getInstance().register(countingIdlingResource);
        }

        @Test
        public void checkExoplayer(){
            onView(ViewMatchers.withId(R.id.main_recyclerview)).
                    perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

            onView(ViewMatchers.withId(R.id.Recyclerview_steps)).
                    perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
            onView(withId(R.id.video)).check(matches(isDisplayed()));
        }

        @After
        public void removeIdlingResource(){
            if(countingIdlingResource != null){
                IdlingRegistry.getInstance().unregister(countingIdlingResource);
            }
        }
}
