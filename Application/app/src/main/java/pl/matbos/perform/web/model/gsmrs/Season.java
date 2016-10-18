package pl.matbos.perform.web.model.gsmrs;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "season")
public class Season {
    @ElementList(name = "round", inline = true)
    public final List<Round> rounds;

    public Season(@ElementList(name = "round", inline = true) List<Round> rounds) {
        this.rounds = rounds;
    }
}