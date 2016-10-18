package pl.matbos.perform.news.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.matbos.perform.common.ConnectionState;
import pl.matbos.perform.news.NewsAdapter;
import pl.matbos.perform.news.NewsFragment;
import pl.matbos.perform.news.NewsModel;
import pl.matbos.perform.news.NewsPresenter;
import pl.matbos.perform.util.LayoutInflaterCache;
import pl.matbos.perform.web.PerformService;
import rx.Observable;

@Module
public class NewsModule {

    private final NewsFragment fragment;

    public NewsModule(NewsFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    public LayoutInflaterCache providesLayoutInflaterCache() {
        return new LayoutInflaterCache(fragment.getActivity());
    }

    @Provides
    @Singleton
    NewsPresenter providesNewsPresenter(NewsModel model, Observable<ConnectionState> connectionState) {
        return new NewsPresenter(fragment, model, connectionState);
    }

    @Provides
    @Singleton
    NewsModel providesNewsModel(PerformService service) {
        return new NewsModel(service);
    }

    @Provides
    NewsAdapter providesNewsAdapter(LayoutInflaterCache cache) {
        return new NewsAdapter(cache);
    }
}