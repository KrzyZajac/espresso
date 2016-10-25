package pl.matbos.perform;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import pl.matbos.perform.host.HostActivity;

import static pl.matbos.perform.TestUtils.isItemClicked;
import static pl.matbos.perform.TestUtils.isItemDisplayed;

public class ScoresTests {

    @Rule
    public ActivityTestRule<HostActivity> mActivityRule = new ActivityTestRule(HostActivity.class);

    @Before
    public void changeToScores(){
        isItemClicked(R.id.host_menu);
        isItemClicked(R.id.host_scores);
    }

    @Test
    public void verifyIfScoreHeaderDisplayed(){
        isItemDisplayed(R.id.score_header_text);
    }

    /**
     * 4. Test if text "Chelsea" is displayed
     * 5. Back to News Page after every test
     **/

}
