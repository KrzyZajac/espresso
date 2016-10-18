package pl.matbos.perform;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.squareup.burst.BurstJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.matbos.perform.host.HostActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(BurstJUnit4.class)
public class NewsTests {

    @Rule
    public ActivityTestRule<HostActivity> mActivityRule = new ActivityTestRule(HostActivity.class);

    @Test
    public void shouldCheckItemsInList(TestUtils.NewsItems newsItems) {
        onView(withId(R.id.news_recycler))
                .perform(RecyclerViewActions.scrollToPosition(newsItems.item))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckItemsIsClickable(TestUtils.NewsItems newsItems) {
        onView(withId(R.id.news_recycler))
                .perform(RecyclerViewActions.scrollToPosition(newsItems.item))
                .check(matches(isClickable()));
    }

    @Test
    public void shouldCheckBackButton() {
        onView(withText("Lead story teaser text here"))
                .perform(click());
        

    }

}
