package pl.matbos.perform.web.model.rss;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
public class RSSResponse {
    @Element(name = "channel")
    public final Channel channel;
    @Attribute(name = "version")
    public final String version;


    public RSSResponse(@Element(name = "channel") Channel channel,
                       @Attribute(name = "version") String version) {
        this.channel = channel;
        this.version = version;
    }
}
