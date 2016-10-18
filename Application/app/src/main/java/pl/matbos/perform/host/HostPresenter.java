package pl.matbos.perform.host;

import pl.matbos.perform.common.ConnectionState;
import pl.matbos.perform.host.model.HostPresenterState;
import pl.matbos.perform.host.model.Screen;
import pl.matbos.perform.util.SubscriptionUtils;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.Subscriptions;

public class HostPresenter {

    private final HostView view;
    private final Observable<ConnectionState> connectionState;

    private boolean menuOpen = false;
    private Subscription subscription = Subscriptions.empty();
    private Screen displayedScreen = Screen.NEWS;

    public HostPresenter(HostView view, Observable<ConnectionState> connectionState) {
        this.view = view;
        this.connectionState = connectionState;
    }

    public void viewReady() {
        loadInitialScreen();
        listenForConnectionChanges();
    }

    public void viewPaused() {
        SubscriptionUtils.unsubscribe(subscription);
    }

    public boolean showMenuOpen() {
        return menuOpen;
    }

    public void menuClicked() {
        if (menuOpen) {
            view.hideMenu();
        } else {
            view.showMenu();
        }
        menuOpen = !menuOpen;
    }

    public void newsMenuItemClicked() {
        displayedScreen = Screen.NEWS;
        view.showNewsSection();
        hideMenu();
    }

    public void scoresMenuItemClicked() {
        displayedScreen = Screen.SCORES;
        view.showScoresSection();
        hideMenu();
    }

    public void standingsMenuItemClicked() {
        displayedScreen = Screen.STANDINGS;
        view.showStandingsSection();
        hideMenu();
    }

    public HostPresenterState getState() {
        return new HostPresenterState(displayedScreen);
    }

    public void restoreState(HostPresenterState state) {
        displayedScreen = state.screen;
    }

    private void loadInitialScreen() {
        switch (displayedScreen) {
            case NEWS:
                newsMenuItemClicked();
                break;
            case SCORES:
                scoresMenuItemClicked();
                break;
            case STANDINGS:
                standingsMenuItemClicked();
                break;
        }
    }

    private void hideMenu() {
        menuOpen = false;
        view.hideMenu();
    }

    private void listenForConnectionChanges() {
        subscription = connectionState
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(state -> {
                    if (state == ConnectionState.CONNECTED) {
                        view.hideAllNotifications();
                    } else {
                        view.showNoInternet();
                    }
                });
    }
}