package pl.matbos.perform.web.model.gsmrs;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "competition")
public class Competition {
    @Element(name = "season")
    public final Season season;

    public Competition(@Element(name = "season") Season season) {
        this.season = season;
    }
}
