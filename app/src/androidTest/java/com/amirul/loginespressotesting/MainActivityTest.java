package com.amirul.loginespressotesting;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/*
 *  ****************************************************************************
 *  * Created by : Md Amirul  Islam on 11/9/2018 at 8.11 PM.
 *  * Email : amirul.csejust@gmail.com
 *  *
 *  * Purpose: To test all element of UI
 *  *
 *  * Last edited by : Md Amirul Islam on 11/9/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */


@LargeTest

public class MainActivityTest {

    // To launch the mentioned activity under testing
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);



    /*
     * Below two method needs if want some DB (Database) or network operation and we need setup some
     * important things like DB connection established or DB close.
     * But now we are not use it
     * */

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void LoginTest() throws Exception{

        // Login if it is on the LoginActivity
        try {

            delay(2000);
            // Type email and password
            Espresso.onView(ViewMatchers.withId(R.id.etEmailInput))
                    .perform(ViewActions.typeText("amirul"), ViewActions.closeSoftKeyboard());


            delay(2000);
            Espresso.onView(ViewMatchers.withId(R.id.etPassInput))
                    .perform(ViewActions.typeText("12345"), ViewActions.closeSoftKeyboard());

            // Click login button
            Espresso.onView(ViewMatchers.withId(R.id.btnSubmit)).perform(ViewActions.click());


            Espresso.onView(withId(R.id.show)).check(matches(textViewHasValue()));


            mActivityRule.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mActivityRule.getActivity(), "show message", Toast.LENGTH_SHORT).show();
                }
            });
            delay(2000);

            // now check toast is properly showing or not

              onView(withText("amirul")).inRoot(withDecorView((is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        } catch (final NoMatchingViewException e) {
            //view not displayed logic

           mActivityRule.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mActivityRule.getActivity(), "show message"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    /*checking textview data */

    Matcher<View> textViewHasValue() {

        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("The TextView/EditText has value");
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextView) && !(view instanceof EditText)) {
                    return false;
                }
                if (view != null) {
                    String text;
                    if (view instanceof TextView) {
                        text = ((TextView) view).getText().toString();
                    } else {
                        text = ((EditText) view).getText().toString();
                    }

                    return (!TextUtils.isEmpty(text));
                }
                return false;
            }
        };
    }

    /*end textview */


    /*checking textview with string*/

    Matcher<View> hasValueEqualTo(final String content) {

        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("Has EditText/TextView the value:  " + content);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextView) && !(view instanceof EditText)) {
                    return false;
                }
                if (view != null) {
                    String text;
                    if (view instanceof TextView) {
                        text = ((TextView) view).getText().toString();
                    } else {
                        text = ((EditText) view).getText().toString();
                    }

                    return (text.equalsIgnoreCase(content));
                }
                return false;
            }
        };
    }

    /*end */

    private void delay(long item) {
        try {
            Thread.sleep(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}