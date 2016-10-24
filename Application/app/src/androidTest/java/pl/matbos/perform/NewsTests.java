package pl.matbos.perform;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.squareup.burst.BurstJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.matbos.perform.host.HostActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static pl.matbos.perform.TestUtils.isItemClicked;
import static pl.matbos.perform.TestUtils.isItemDisplayed;
import static pl.matbos.perform.TestUtils.isTextDisplayed;

@RunWith(BurstJUnit4.class)
public class NewsTests {

    @Rule
    public ActivityTestRule<HostActivity> mActivityRule = new ActivityTestRule(HostActivity.class);

    @Test
    public void verifyIfDownHexIconDisplayed() {
        isItemDisplayed(R.id.menu_news_refresh);
    }

    @Test
    public void verifyIfRefreshIconDisplayed() {
        isItemDisplayed(R.id.host_menu);
    }

    @Test
    public void shouldCheckItemsOfExpandedDropList(TestUtils.MenuItems menuItems) {
        isItemClicked(R.id.host_menu);
        isTextDisplayed(menuItems.item);
    }

    @Test
    public void shouldCheckChildrenOfMenu(){
        isItemClicked(R.id.host_menu);
        onView(allOf(withId(R.id.host_news), isDescendantOfA(withId(R.id.host_menu_layout))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckScoresIsAboveNews(){
        onView(withId(R.id.host_scores)).check(isAbove(withId(R.id.host_news)));
    }

    @Test
    public void shouldScrollToLastElement() {
        onView(withId(R.id.news_recycler)).perform(swipeUp());
        onView(withText("10th story teaser text here"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldCheckItemsInList(TestUtils.NewsItems newsItems) {
        onView(withId(R.id.news_recycler))
                .perform(RecyclerViewActions.scrollToPosition(newsItems.item))
                .check(matches(isDisplayed()));
    }

}
