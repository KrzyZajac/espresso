package pl.matbos.perform.web.model.gsmrs;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "group")
public class Group {
    @ElementList(name = "match", inline = true)
    public final List<Match> matches;

    public Group(@ElementList(name = "match", inline = true) List<Match> matches) {
        this.matches = matches;
    }
}