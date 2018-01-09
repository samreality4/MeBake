package com.example.sam.mebake;

import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.sql.Statement;

import static android.support.test.espresso.Espresso.onView;


/**
 * Created by sam on 1/8/18.
 */
@RunWith(JUnit4.class)
@LargeTest
public class TestActivity {

    @Rule
    public TestRule mTestRule = new TestRule() {
        @Override
        public Statement apply(Statement base, Description description) {
            return null;
        }

    }

        @Test
        public void RecipeName_Check()[]{
                onView()
        }

    }

}