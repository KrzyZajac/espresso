package pl.matbos.perform.scores.model;

import org.joda.time.DateTime;

import java.util.List;

public class ScoreBoard {

    public final DateTime date;
    public final List<Score> scores;

    public ScoreBoard(DateTime date, List<Score> scores) {
        this.date = date;
        this.scores = scores;
    }
}
