package pl.matbos.perform.scores.converter;

import pl.matbos.perform.scores.model.Score;
import pl.matbos.perform.web.model.gsmrs.Match;

public final class ScoresConverter {

    private ScoresConverter() {
    }

    public static Score from(Match match) {
        return new Score(match.teamA, match.teamB, match.scoreA, match.scoreB);
    }
}