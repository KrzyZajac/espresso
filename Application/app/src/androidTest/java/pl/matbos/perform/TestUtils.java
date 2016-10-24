package pl.matbos.perform;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class TestUtils {

    public static void isItemDisplayed(int item) {
        onView(withId(item)).check(matches(isDisplayed()));
    }

    public static void isTextDisplayed(String text) {
        onView(withText(text)).check(matches(isDisplayed()));
    }

    public static void isItemClicked(int item) {
        onView(withId(item)).perform(click());
    }

    public enum MenuItems {
        NEWS("News"),
        SCORES("Scores"),
        STANDINGS("Standings");
        String item;

        MenuItems(String item) {
            this.item = item;
        }
    }

    public enum NewsItems {
        LEAD_STORY(0,"Lead story teaser text here"),
        SECOND_STORY(1,"2nd story teaser text here"),
        THIRD_STORY(2,"3rd story teaser text here"),
        FOURTH_STORY(3,"4th story teaser text here"),
        FIFTH_STORY(4,"5th story teaser text here"),
        SIXTH_STORY(5,"6th story teaser text here"),
        SEVENTH_STORY(6,"7th story teaser text here"),
        EIGHTH_STORY(7,"8th story teaser text here"),
        NINTH_STORY(8,"9th story teaser text here"),
        TENTH_STORY(9,"10th story teaser text here");
        String title;
        int item;

        NewsItems(int item, String title) {
            this.item = item;
            this.title = title;
        }
    }
}
