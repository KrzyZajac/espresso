package pl.matbos.perform.web.model.gsmrs;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "method")
public class Method {

    @ElementList(name = "parameter", inline = true)
    public final List<Parameter> parameters;

    public Method(@ElementList(name = "parameter", inline = true) List<Parameter> parameters) {
        this.parameters = parameters;
    }
}