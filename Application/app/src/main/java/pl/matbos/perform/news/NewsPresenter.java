package pl.matbos.perform.news;

import java.util.concurrent.atomic.AtomicBoolean;

import pl.matbos.perform.common.ConnectionState;
import pl.matbos.perform.news.model.Feed;
import pl.matbos.perform.util.SubscriptionUtils;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class NewsPresenter {

    private final NewsView view;
    private final NewsModel model;
    private final Observable<ConnectionState> connectionState;
    private final AtomicBoolean operationFlag = new AtomicBoolean(false);

    private Subscription feedSubscription;

    public NewsPresenter(NewsView view, NewsModel model, Observable<ConnectionState> connectionState) {
        this.view = view;
        this.model = model;
        this.connectionState = connectionState;
    }

    public void viewReady() {
        loadFeed();
    }

    public void viewPaused() {
        SubscriptionUtils.unsubscribe(feedSubscription);
    }

    public void reloadRequested() {
        loadFeed();
    }

    public void loadFeed() {
        if (operationFlag.compareAndSet(false, true)) {
            SubscriptionUtils.unsubscribe(feedSubscription);
            feedSubscription =
                    connectionState.filter(state -> state == ConnectionState.CONNECTED)
                            .doOnNext(ignored -> view.showProgress())
                            .flatMap(ignored -> model.getFeed()
                                    .subscribeOn(Schedulers.io()))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(feed -> {
                                        handleOperation(feed);
                                        operationFlag.set(false);
                                    },
                                    throwable -> {
                                        Timber.d(throwable, throwable.getMessage());
                                        view.errorOccured();
                                        operationFlag.set(false);
                                    });
        }
    }

    private void handleOperation(Feed feed) {
        if (feed.news.isEmpty()) {
            view.showEmpty();
        } else {
            view.hideProgress();
            view.setNews(feed.news);
            view.setTitle(feed.title);
        }
    }
}