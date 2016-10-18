package pl.matbos.perform.web.model.rss;

import org.joda.time.DateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class Article {
    @Element(name = "guid")
    public final String guid;
    @Element(name = "title")
    public final String title;
    @Element(name = "description")
    public final String description;
    @Element(name = "pubDate")
    public final DateTime publicationDate;
    @Element(name = "enclosure")
    public final Link imageUrl;
    @Element(name = "category")
    public final String category;
    @Element(name = "link")
    public final String articleUrl;

    public Article(@Element(name = "guid") String guid,
                   @Element(name = "title") String title,
                   @Element(name = "description") String description,
                   @Element(name = "pubDate") DateTime publicationDate,
                   @Element(name = "enclosure") Link imageUrl,
                   @Element(name = "category") String category,
                   @Element(name = "link") String articleUrl) {
        this.guid = guid;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.imageUrl = imageUrl;
        this.category = category;
        this.articleUrl = articleUrl;
    }
}
