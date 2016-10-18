package pl.matbos.perform.web.model.gsmrs;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "gsmrs")
public class GSMRSResponse {
    @Element(name = "method")
    public final Method method;
    @Element(name = "competition")
    public final Competition competition;

    public GSMRSResponse(@Element(name = "method") Method method, @Element(name = "competition") Competition competition) {
        this.method = method;
        this.competition = competition;
    }
}