package pl.matbos.perform.host;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.matbos.perform.R;
import pl.matbos.perform.common.ui.StateNotificationLayout;
import pl.matbos.perform.host.di.HostComponent;
import pl.matbos.perform.host.model.HostPresenterState;
import pl.matbos.perform.news.NewsFragment;
import pl.matbos.perform.scores.ScoresFragment;
import pl.matbos.perform.standing.StandingsFragment;

public class HostActivity extends AppCompatActivity implements HostView, StateShow {
    private static final String NEWS_TAG = "NEWS";
    private static final String SCORES_TAG = "SCORES";
    private static final String STANDINGS_TAG = "STANDINGS";
    private static final String PRESENTER_STATE_KEY = HostActivity.class.getCanonicalName() + "_PRESENTER_STATE_KEY";

    @BindView(R.id.host_menu_layout)
    protected LinearLayout menuLayout;
    @BindView(R.id.host_content_frame)
    protected FrameLayout contentFrame;
    @BindView(R.id.host_notification_layout)
    protected StateNotificationLayout notificationLayout;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Inject
    protected HostPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        ButterKnife.bind(this);
        HostComponent.Injector.inject(this);
        setSupportActionBar(toolbar);
        restorePresenterState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.host_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.host_menu);
        item.setIcon(presenter.showMenuOpen() ? R.drawable.up_hex : R.drawable.down_hex);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.host_menu) {
            presenter.menuClicked();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PRESENTER_STATE_KEY, presenter.getState());
    }

    private void restorePresenterState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        HostPresenterState state = (HostPresenterState) savedInstanceState.getSerializable(PRESENTER_STATE_KEY);
        if (state != null) {
            presenter.restoreState(state);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.viewReady();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.viewPaused();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        }
        super.onBackPressed();
    }

    @Override
    public void showProgress() {
        hideContent();
        notificationLayout.showProgress();
    }

    @Override
    public void hideProgress() {
        showContent();
        notificationLayout.hideProgress();
    }

    @Override
    public void errorOccured(String errorMessage) {
        hideContent();
        notificationLayout.showError(errorMessage);
    }

    @Override
    public void hideNotifications() {
        notificationLayout.hideAllNotifications();
    }

    @Override
    public void showEmpty(String message) {
        notificationLayout.showError(message, R.drawable.empty);
    }

    @OnClick(R.id.host_news)
    public void onNewsClicked() {
        presenter.newsMenuItemClicked();
    }

    @OnClick(R.id.host_scores)
    public void onScoresClicked() {
        presenter.scoresMenuItemClicked();
    }

    @OnClick(R.id.host_standings)
    public void onStandingsClicked() {
        presenter.standingsMenuItemClicked();
    }

    @Override
    public void showMenu() {
        menuLayout.setVisibility(View.VISIBLE);
        invalidateOptionsMenu();
    }

    @Override
    public void hideMenu() {
        menuLayout.setVisibility(View.GONE);
        invalidateOptionsMenu();
    }

    @Override
    public void showNewsSection() {
        NewsFragment fragment = new NewsFragment();
        fragment.setStateShow(this);
        displayFragment(fragment, NEWS_TAG);
    }

    @Override
    public void showScoresSection() {
        ScoresFragment fragment = new ScoresFragment();
        fragment.setStateShow(this);
        displayFragment(fragment, SCORES_TAG);
    }

    @Override
    public void showStandingsSection() {
        StandingsFragment fragment = new StandingsFragment();
        fragment.setStateShow(this);
        displayFragment(fragment, STANDINGS_TAG);
    }

    @Override
    public void showNoInternet() {
        hideContent();
        notificationLayout.showError(getString(R.string.no_internet_message), R.drawable.airplane_off);
    }

    @Override
    public void hideAllNotifications() {
        showContent();
        notificationLayout.hideAllNotifications();
    }

    private void displayFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .replace(R.id.host_content_frame, fragment);
        if (shouldAddToBackstack(fragmentManager, tag)) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    private boolean shouldAddToBackstack(FragmentManager fragmentManager, String tag) {
        int count = fragmentManager.getBackStackEntryCount();
        if (count == 0) {
            return true;
        }
        return !fragmentManager.getBackStackEntryAt(count - 1).getName().equals(tag);
    }

    private void hideContent() {
        contentFrame.setVisibility(View.GONE);
    }

    private void showContent() {
        contentFrame.setVisibility(View.VISIBLE);
    }
}