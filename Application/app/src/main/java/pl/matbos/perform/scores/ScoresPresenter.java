package pl.matbos.perform.scores;

import pl.matbos.perform.common.ConnectionState;
import pl.matbos.perform.scores.model.ScoreBoard;
import pl.matbos.perform.util.SubscriptionUtils;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;
import timber.log.Timber;

public class ScoresPresenter {

    private final ScoresModel model;
    private final ScoresView view;
    private final Observable<ConnectionState> connectionState;

    private Subscription dataSubscription = Subscriptions.empty();

    public ScoresPresenter(ScoresView view, ScoresModel model, Observable<ConnectionState> connectionState) {
        this.view = view;
        this.model = model;
        this.connectionState = connectionState;
    }

    public void viewReady() {
        loadData(view);
    }

    public void viewPaused() {
        SubscriptionUtils.unsubscribe(dataSubscription);
    }

    public void loadData(ScoresView view) {
        view.showProgress();
        dataSubscription = connectionState
                .filter(state -> state.equals(ConnectionState.CONNECTED))
                .flatMap(ignored -> model.getScores()
                        .subscribeOn(Schedulers.io()))
                .doOnNext(ignored -> view.hideProgress())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(scoreBoard -> handleNewData(view, scoreBoard),
                        throwable -> {
                            view.errorOccured(throwable.getMessage());
                            Timber.d(throwable, throwable.getMessage());
                        });
    }

    private void handleNewData(ScoresView view, ScoreBoard scoreBoard) {
        if (scoreBoard.scores.isEmpty()) {
            view.showEmpty();
        } else {
            view.hideProgress();
            view.setScores(scoreBoard);
        }
    }
}