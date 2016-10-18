package pl.matbos.perform.web.converter;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.io.InputStreamReader;

import pl.matbos.perform.util.DateUtils;
import pl.matbos.perform.web.model.rss.RSSResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeElementConverterTest {

    private static Persister serializer;
    private static DateTime referenceDate;

    @Test
    public void testCorrectDate() throws Exception {
        final InputStream xml = ClassLoader.getSystemResourceAsStream("date_ok.xml");
        final InputStreamReader stream = new InputStreamReader(xml);
        RSSResponse rssResponse = serializer.read(RSSResponse.class, stream, false);

        assertThat(rssResponse.channel.publicationDate).isEqualTo(referenceDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMalformedDate() throws Exception {
        final InputStream xml = ClassLoader.getSystemResourceAsStream("date_fail.xml");
        final InputStreamReader stream = new InputStreamReader(xml);
        serializer.read(RSSResponse.class, stream, false);
    }

    @BeforeClass
    public static void before() throws Exception {
        referenceDate = DateTime.parse("Tue, 01 Jan 2013 18:00:00 +0000", DateUtils.SERVER_DATE_FORMATTER);
        Registry registry = new Registry();
        registry.bind(DateTime.class, DateTimeElementConverter.class);
        serializer = new Persister(new RegistryStrategy(registry));
    }
}