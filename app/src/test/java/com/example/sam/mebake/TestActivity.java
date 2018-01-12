package com.example.sam.mebake;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.sql.Statement;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by sam on 1/8/18.
 */
@RunWith(JUnit4.class)
@LargeTest
public class TestActivity {

    @Rule
    public ActivityTestRule<RecipeDetail> activityTestRule = new ActivityTestRule<>(RecipeDetail.class);

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                ViewParent parent = item.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && item.equals(((ViewGroup)parent).getChildAt(position));
            }

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("Child at position" + position + "in parent");
                parentMatcher.describeTo(description);

            }
        };
    }
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId){
        return new RecyclerviewMatcher(recyclerViewId);
    }

    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }


        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if(noViewFoundException !=null){
                throw noViewFoundException;
            }

            RecyclerView recyclerView= (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));

        }
    }

    @Test
    public void mainAcitivityTest(){
        onView((withRecyclerView(R.)
        .atPositionOnView(0, ))
        .check(matches(withText("Brownies")));
        onView(withId(R.id.main_recyclerview)).check(new RecyclerViewItemCountAssertion(4));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.main_recyclerview)
                ,isDisplayed());
                recyclerView.perform(actionOnItemAtPosition(0, click())));
        )
    }


}