package com.amirul.loginespressotesting;

import android.support.annotation.StringRes;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.provider.Settings.System.getString;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;


/*
 *  ****************************************************************************
 *  * Created by : Md Amirul  Islam on 11/9/2018 at 8.11 PM.
 *  * Email : amirul.csejust@gmail.com
 *  *
 *  * Purpose: To test all element of UI
 *  *
 *  * Last edited by : Md Amirul Islam on 11/11/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */



@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {


    // To launch the mentioned activity under testing
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);




    @Test
    public void emailIsEmpty() {
        delay(2000);
        onView(withId(R.id.etEmailInput)).perform(clearText());
        delay(2000);
        onView(withId(R.id.btnSubmit)).perform(click());
        delay(2000);
        onView(withId(R.id.etEmailInput)).check(matches(withError(getString(R.string.error_field_required))));
        delay(2000);
    }

    @Test
    public void passwordIsEmpty() {
        delay(2000);
        onView(withId(R.id.etEmailInput)).perform(typeText("email@email.com"), closeSoftKeyboard());
        delay(2000);
        onView(withId(R.id.etPassInput)).perform(clearText());
        delay(2000);
        onView(withId(R.id.btnSubmit)).perform(click());
        delay(2000);
        onView(withId(R.id.etPassInput)).check(matches(withError(getString(R.string.error_field_required))));
        delay(2000);
    }

    @Test
    public void emailIsInvalid() {
        delay(2000);
        onView(withId(R.id.etEmailInput)).perform(typeText("invalid"), closeSoftKeyboard());
        delay(2000);
        onView(withId(R.id.btnSubmit)).perform(click());
        delay(2000);
        onView(withId(R.id.etEmailInput)).check(matches(withError(getString(R.string.error_invalid_email))));
        delay(2000);
    }

    @Test
    public void passwordIsTooShort() {
        delay(2000);
        onView(withId(R.id.etEmailInput)).perform(typeText("amir@email.com"), closeSoftKeyboard());
        delay(2000);
        onView(withId(R.id.etPassInput)).perform(typeText("1234"), closeSoftKeyboard());
        delay(2000);
        onView(withId(R.id.btnSubmit)).perform(click());
        delay(2000);
        onView(withId(R.id.etPassInput)).check(matches(withError(getString(R.string.error_invalid_password))));
        delay(2000);
    }

    @Test
    public void loginFailed() {
        delay(2000);
        onView(withId(R.id.etEmailInput)).perform(typeText("incorrect@email.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassInput)).perform(typeText("123456"), closeSoftKeyboard());
        delay(2000);
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText(getString(R.string.error_login_failed)))
                .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        delay(2000);
    }

    @Test
    public void loginSuccessfully_shouldShowWelcomeMessage() {
        delay(2000);
        onView(withId(R.id.etEmailInput)).perform(typeText("amir@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassInput)).perform(typeText("123456"), closeSoftKeyboard());
        delay(2000);
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withId(R.id.tv_welcome)).check(matches(withText("Hi amir@gmail.com!")));
    }

    @Test
    public void loginSuccessfully_shouldShowToast() {

        delay(2000);
        onView(withId(R.id.etEmailInput)).perform(typeText("amir@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassInput)).perform(typeText("123456"), closeSoftKeyboard());
        delay(2000);
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText(getString(R.string.login_successfully)))
                .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        delay(2000);
    }

    private String getString(@StringRes int resourceId) {
        return activityTestRule.getActivity().getString(resourceId);
    }

    private static Matcher<View> withError(final String expected) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof EditText) {
                    return ((EditText)item).getError().toString().equals(expected);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Not found error message" + expected + ", find it!");
            }
        };
    }

    private void delay(long item) {
        try {
            Thread.sleep(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}