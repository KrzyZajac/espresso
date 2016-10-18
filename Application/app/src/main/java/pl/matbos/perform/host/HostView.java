package pl.matbos.perform.host;

public interface HostView {
    void showMenu();
    void hideMenu();

    void showNewsSection();
    void showScoresSection();
    void showStandingsSection();

    void showNoInternet();
    void hideAllNotifications();
}
