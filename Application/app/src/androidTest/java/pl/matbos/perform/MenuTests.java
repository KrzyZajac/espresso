package pl.matbos.perform;

import android.support.test.rule.ActivityTestRule;

import com.squareup.burst.BurstJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.matbos.perform.host.HostActivity;

import static pl.matbos.perform.TestUtils.isItemClicked;
import static pl.matbos.perform.TestUtils.isItemDisplayed;
import static pl.matbos.perform.TestUtils.isTextDisplayed;

@RunWith(BurstJUnit4.class)
public class MenuTests {

    @Rule
    public ActivityTestRule<HostActivity> mActivityRule = new ActivityTestRule(HostActivity.class);

    @Test
    public void verifyDownHexIcon() {
        isItemDisplayed(R.id.menu_news_refresh);
    }

    @Test
    public void verifyRefreshIcon() {
        isItemDisplayed(R.id.host_menu);
    }

    @Test
    public void shouldExpandDropList() {
        isItemClicked(R.id.host_menu);
    }

    @Test
    public void shouldCheckItemsOfExpandedDropList(TestUtils.MenuItems menuItems) {
        isItemClicked(R.id.host_menu);
        isTextDisplayed(menuItems.item);
    }

}
