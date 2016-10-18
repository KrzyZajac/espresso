package pl.matbos.perform.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class DateUtils {
    /**
     * Handles this format: Tue, 01 Jan 2013 18:00:00 +0000
     */
    public static final DateTimeFormatter SERVER_DATE_FORMATTER = DateTimeFormat.forPattern("EEE, dd MMM YYYY HH:mm:ss Z");
    /**
     * Handles this format: 2016-09-21
     */
    public static final DateTimeFormatter SERVER_SHORT_DATE_FORMATTER = DateTimeFormat.forPattern("YYYY-MM-dd");
    /**
     * Handles this format: Wednesday, 7 December 2011 13:35
     */
    public static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormat.forPattern("EEEE, d MMMM YYYY, HH:mm");

    /**
     * Handes this format: 17 April 2016
     */
    public static final DateTimeFormatter DISPLAY_SHORT_DATE_FORMATTER = DateTimeFormat.forPattern("d MMMM YYYY");

    private DateUtils() {
    }
}
