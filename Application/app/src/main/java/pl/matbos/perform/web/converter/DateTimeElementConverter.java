package pl.matbos.perform.web.converter;

import org.joda.time.DateTime;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import pl.matbos.perform.util.DateUtils;

/**
 * Converts String to DateTime representation. Reverse process is not required here.
 */
public class DateTimeElementConverter implements Converter<DateTime> {

    @Override
    public DateTime read(InputNode node) throws Exception {
        if (node.isElement()) {
            String value = node.getValue();
            if (value != null) {
                return DateUtils.SERVER_DATE_FORMATTER.parseDateTime(value);
            }
        }
        return null;
    }

    @Override
    public void write(OutputNode node, DateTime value) throws Exception {
        // Simply not needed for this project
    }
}