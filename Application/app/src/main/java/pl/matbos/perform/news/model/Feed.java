package pl.matbos.perform.news.model;

import java.util.List;

public class Feed {
    public static final Feed EMPTY = new Feed(null, null);

    public final String title;
    public final List<News> news;

    public Feed(String title, List<News> news) {
        this.title = title;
        this.news = news;
    }
}