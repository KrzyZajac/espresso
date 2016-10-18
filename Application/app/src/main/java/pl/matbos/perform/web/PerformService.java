package pl.matbos.perform.web;

import pl.matbos.perform.web.model.gsmrs.GSMRSResponse;
import pl.matbos.perform.web.model.rss.RSSResponse;
import retrofit2.http.GET;
import rx.Observable;

public interface PerformService {

    String ENDPOINT = "http://www.mobilefeeds.performgroup.com/utilities/interviews/techtest/";

    @GET("latestnews.xml")
    Observable<RSSResponse> getLatestNews();

    @GET("scores.xml")
    Observable<GSMRSResponse> getScores();

    @GET("standings.xml")
    Observable<GSMRSResponse> getStandings();
}