package pl.matbos.perform.standing;

import java.util.List;
import java.util.concurrent.TimeUnit;

import pl.matbos.perform.common.ConnectionState;
import pl.matbos.perform.standing.model.RankingEntry;
import pl.matbos.perform.util.SubscriptionUtils;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;
import timber.log.Timber;

public class StandingsPresenter {

    private final StandingsModel model;
    private final Observable<ConnectionState> connectionState;
    private final StandingsView view;

    private Subscription standingsSubscription = Subscriptions.empty();

    public StandingsPresenter(StandingsView view, StandingsModel model, Observable<ConnectionState> connectionState) {
        this.view = view;
        this.model = model;
        this.connectionState = connectionState;
    }

    public void viewReady() {
        loadData();
    }

    public void viewPaused() {
        SubscriptionUtils.unsubscribe(standingsSubscription);
    }

    public void loadData() {
        SubscriptionUtils.unsubscribe(standingsSubscription);
        standingsSubscription =
                connectionState.filter(state -> state.equals(ConnectionState.CONNECTED))
                        .doOnNext(ignored -> view.showProgress())
                        .delay(1, TimeUnit.SECONDS)
                        .flatMap(ignored -> model.getRankings()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread()))
                        .subscribe(this::handleStandingsData,
                                throwable -> {
                                    view.errorOccured(throwable.getMessage());
                                    Timber.d(throwable, throwable.getMessage());
                                });
    }

    private void handleStandingsData(List<RankingEntry> ranking) {
        if (ranking.isEmpty()) {
            view.showEmpty();
        } else {
            view.hideProgress();
            view.setRanking(ranking);
        }
    }
}