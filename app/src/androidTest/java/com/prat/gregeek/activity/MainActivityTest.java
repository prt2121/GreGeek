package com.prat.gregeek.activity;

import com.prat.gregeek.R;

import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by pt2121 on 2/15/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Activity mActivity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        // http://dev.jimdo.com/2014/05/09/wait-for-it-a-deep-dive-into-espresso-s-idling-resources/
    }

    public void testDefinitionTextView() {
        onView(withId(R.id.dailyWordDefinition))
                .check(matches(withText(containsString("definition"))));
    }

    public void testClickDefinitionTextView() {
        onView(withId(R.id.dailyWordDefinition))
                .check(matches(withText("definition?")));
        onView(withId(R.id.dailyWordDefinition))
                .perform(ViewActions.click());
        onView(withId(R.id.dailyWordDefinition))
                .check(matches(withText("definition?")));
    }

    public void testClickSynonymTextView() {
        onView(withId(R.id.dailyWordSynonym))
                .check(matches(withText("synonym?")));
        onView(withId(R.id.dailyWordSynonym))
                .perform(ViewActions.click());
        onView(withId(R.id.dailyWordSynonym))
                .check(matches(withText("synonym?")));
    }

    // TODO fix this. always pass
    /*public void testActivityTitle() {
        onView(allOf(isDescendantOfA(withResourceName("android:id/action_bar_container")),
                withText(R.string.title_word_of_the_day)))
                .check(matches(isDisplayed()));
    }

    public static Matcher<View> withResourceName(String resourceName) {
        return withResourceName(is(resourceName));
    }

    public static Matcher<View> withResourceName(final Matcher<String> resourceNameMatcher) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with resource name: ");
                resourceNameMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                int id = view.getId();
                return id != View.NO_ID && id != 0 && view.getResources() != null
                        && resourceNameMatcher.matches(view.getResources().getResourceName(id));
            }
        };
    }*/


}
