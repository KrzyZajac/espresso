package pl.matbos.perform.web.model.gsmrs;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "parameter")
public class Parameter {
    @Attribute(name = "name")
    public final String name;
    @Attribute(name = "value")
    public final String value;

    public Parameter(@Attribute(name = "name") String name,
                     @Attribute(name = "value") String value) {
        this.name = name;
        this.value = value;
    }
}
