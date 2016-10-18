package pl.matbos.perform.web.model.rss;

import org.joda.time.DateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel", strict = false)
public class Channel {
    @Element(name = "title")
    public final String title;
    @Element(name = "descriprion", required = false)
    public final String description;
    @Element(name = "language")
    public final String language;
    @Element(name = "pubDate")
    public final DateTime publicationDate;
    @ElementList(name = "item", inline = true)
    public final List<Article> articles;

    public Channel(@Element(name = "title") String title,
                   @Element(name = "descriprion") String description,
                   @Element(name = "language") String language,
                   @Element(name = "pubDate") DateTime publicationDate,
                   @ElementList(name = "item") List<Article> articles) {
        this.title = title;
        this.description = description;
        this.language = language;
        this.publicationDate = publicationDate;
        this.articles = articles;
    }
}
