package pl.matbos.perform.web.model.gsmrs;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "resultstable")
public class ResultsTable {
    @ElementList(name = "ranking", inline = true)
    public final List<Ranking> rankings;

    public ResultsTable(@ElementList(name = "ranking", inline = true) List<Ranking> rankings) {
        this.rankings = rankings;
    }
}
