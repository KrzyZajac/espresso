package pl.matbos.perform.news;

import pl.matbos.perform.common.SimplePair;
import pl.matbos.perform.news.converter.NewsModelConverter;
import pl.matbos.perform.news.model.Feed;
import pl.matbos.perform.web.PerformService;
import rx.Observable;

public class NewsModel {

    private final PerformService service;

    public NewsModel(PerformService service) {
        this.service = service;
    }

    public Observable<Feed> getFeed() {
        return service.getLatestNews()
                .map(rss -> rss.channel)
                .map(channel -> new SimplePair<>(channel.title, channel.articles))
                .flatMap(pair -> Observable.from(pair.second)
                        .map(NewsModelConverter::from)
                        .toList()
                        .map(news -> new Feed(pair.first, news))
                );
    }
}