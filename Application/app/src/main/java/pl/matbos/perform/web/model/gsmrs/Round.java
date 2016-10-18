package pl.matbos.perform.web.model.gsmrs;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "round")
public class Round {
    @ElementList(name = "group", inline = true, required = false)
    public final List<Group> groups;
    @Element(name = "resultstable", required = false)
    public final ResultsTable resultsTable;

    public Round(@ElementList(name = "group", inline = true, required = false) List<Group> groups,
                 @Element(name = "resultstable", required = false) ResultsTable resultsTable) {
        this.groups = groups;
        this.resultsTable = resultsTable;
    }
}
