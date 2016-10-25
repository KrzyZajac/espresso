package pl.matbos.perform;


import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import pl.matbos.perform.host.HostActivity;

import static pl.matbos.perform.TestUtils.isTextDisplayed;

public class StandingsTests {

    @Rule
    public ActivityTestRule<HostActivity> mActivityRule = new ActivityTestRule(HostActivity.class);

    /**
     *  1. Check why test "shouldCheckElementsOfTable" doesn't work and fix it?
     **/
    @Test
    public void shouldCheckElementsOfTable() {
        isTextDisplayed("Manchester City");
    }

    /**
     * 2. Test that DownHex icon is displayed
     * 3. Write test verifying back button in Android (use "pressBack()")
     **/


}
