package pl.matbos.perform.web.model.rss;

import org.simpleframework.xml.Attribute;

public class Link {
    @Attribute(name = "url", required = false)
    public final String url;

    @Attribute(required = false)
    public final String rel;

    @Attribute(name = "length", required = false)
    public final String length;

    @Attribute(name = "type", required = false)
    public final String contentType;

    public Link(@Attribute(name = "url") String url,
                @Attribute(name = "rel") String rel,
                @Attribute(name = "length") String length,
                @Attribute(name = "type") String contentType) {
        this.url = url;
        this.rel = rel;
        this.length = length;
        this.contentType = contentType;
    }
}