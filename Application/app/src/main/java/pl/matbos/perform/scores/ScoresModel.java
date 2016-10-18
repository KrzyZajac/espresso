package pl.matbos.perform.scores;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;

import java.util.List;
import java.util.concurrent.TimeUnit;

import pl.matbos.perform.scores.converter.ScoresConverter;
import pl.matbos.perform.scores.model.Score;
import pl.matbos.perform.scores.model.ScoreBoard;
import pl.matbos.perform.util.DateUtils;
import pl.matbos.perform.web.PerformService;
import pl.matbos.perform.web.model.gsmrs.GSMRSResponse;
import rx.Observable;

public class ScoresModel {

    public static final String DATE_PARAMETER_NAME = "date";
    private final PerformService service;

    public ScoresModel(PerformService service) {
        this.service = service;
    }

    public Observable<ScoreBoard> getScores() {
        return Observable.interval(0, 30, TimeUnit.SECONDS)
                .delay(1, TimeUnit.SECONDS) // Left here to longer show progress
                .flatMap(ignored -> service.getScores())
                .flatMap(games -> extractScoreListing(games)
                        .flatMap(scores -> extractGameDate(games)
                                .map(date -> new ScoreBoard(date, scores))
                        )
                );
    }

    @NonNull
    private Observable<List<Score>> extractScoreListing(GSMRSResponse games) {
        return Observable.from(games.competition.season.rounds.get(0).groups)
                .map(group -> group.matches)
                .flatMap(Observable::from)
                .map(ScoresConverter::from)
                .toList();
    }

    @NonNull
    private Observable<DateTime> extractGameDate(GSMRSResponse games) {
        return Observable.from(games.method.parameters)
                .filter(parameter -> parameter.name.equalsIgnoreCase(DATE_PARAMETER_NAME))
                .map(parameter1 -> DateUtils.SERVER_SHORT_DATE_FORMATTER.parseDateTime(parameter1.value));
    }
}