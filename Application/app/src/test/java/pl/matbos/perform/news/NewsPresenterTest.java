package pl.matbos.perform.news;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import pl.matbos.perform.runner.RxImmediateRunner;
import pl.matbos.perform.common.ConnectionState;
import pl.matbos.perform.news.model.Feed;
import pl.matbos.perform.news.model.News;
import rx.Observable;
import rx.android.plugins.RxAndroidPlugins;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RxImmediateRunner.class)
public class NewsPresenterTest {

    private final List<News> oneItemList = Collections.singletonList(new News("", "", "", DateTime.now(), "", "", ""));
    private NewsPresenter presenter;
    private NewsModel model;
    private Observable<ConnectionState> stateObservable;
    private NewsView view;

    @Before
    public void before() {
        view = mock(NewsView.class);
        model = mock(NewsModel.class);
        stateObservable = Observable.just(ConnectionState.CONNECTED);
        presenter = new NewsPresenter(view, model, stateObservable);
    }

    @After
    public void after() {
        Mockito.reset(model, view);
        RxAndroidPlugins.getInstance().reset();
    }

    @Test
    public void readySignalShouldCallShowEmptyWhenListIsEmpty() throws Exception {
        //Given
        when(model.getFeed()).thenReturn(Observable.just(new Feed("Super Feed", Collections.emptyList())));

        //When
        presenter.viewReady();

        //Then
        verify(view).showEmpty();
    }

    @Test
    public void readySignalShouldCallShowProgress() throws Exception {
        //Given
        when(model.getFeed()).thenReturn(Observable.just(new Feed("Super Feed", Collections.emptyList())));

        //When
        presenter.viewReady();

        //Then
        verify(view).showProgress();
    }

    @Test
    public void readySignalShouldCallHideProgressWhenListIsNotEmpty() throws Exception {
        //Given
        when(model.getFeed()).thenReturn(Observable.just(new Feed("Super Feed", oneItemList)));

        //When
        presenter.viewReady();

        //Then
        verify(view).hideProgress();
    }

    @Test
    public void readySignalShouldCallSetNewsWhenListIsNotEmpty() throws Exception {
        //Given
        when(model.getFeed()).thenReturn(Observable.just(new Feed("", oneItemList)));

        //When
        presenter.viewReady();

        //Then
        verify(view).setNews(oneItemList);
    }

    @Test
    public void readySignalShouldCallSetTitleWhenListIsNotEmpty() throws Exception {
        //Given
        when(model.getFeed()).thenReturn(Observable.just(new Feed("Title test", oneItemList)));

        //When
        presenter.viewReady();

        //Then
        verify(view).setTitle("Title test");
    }

    @Test
    public void readySignalShouldCallErrorOccuredWhenExceptionInGetFeedMethod() throws Exception {
        //Given
        when(model.getFeed()).thenReturn(Observable.error(new Throwable("Test Exception!")));

        //When
        presenter.viewReady();

        //Then
        verify(view).errorOccured();
    }

    @Test
    public void reloadFeedShouldCallThroughToGetFeed() throws Exception {
        //Given

        //When
        presenter.reloadRequested();

        //Then
        verify(model).getFeed();
    }
}