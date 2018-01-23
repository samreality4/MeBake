package com.example.sam.mebake;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void addIdlingResource() {
        idlingResource = mActivityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);

    }

    @Test
    public void checkTextMainActivity(){
        onView(ViewMatchers.withId(R.id.main_recyclerview)).perform(RecyclerViewActions.scrollToPosition((1)));
        onView(withText("Brownies")).check(matches(isDisplayed()));
    }

    @Test
    public void checkExoplayer(){
        onView(ViewMatchers.withId(R.id.main_recyclerview)).
                perform(RecyclerViewActions.scrollToPosition(1));

        onView(ViewMatchers.withId(R.id.Recyclerview_steps)).
                perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.video)).check(matches(isDisplayed()));
    }

    @After
    public void removeIdlingResource(){
        if(idlingResource != null){
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }


}
