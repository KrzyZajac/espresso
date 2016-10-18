package pl.matbos.perform.news.model;

import org.joda.time.DateTime;

public class News {
    public final String guid;
    public final String title;
    public final String description;
    public final DateTime publicationDate;
    public final String imageUrl;
    public final String articleUrl;
    public final String category;

    public News(String guid, String title, String description, DateTime publicationDate, String imageUrl, String articleUrl, String category) {
        this.guid = guid;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.imageUrl = imageUrl;
        this.articleUrl = articleUrl;
        this.category = category;
    }
}
