package pl.matbos.perform.standing.converter;

import pl.matbos.perform.standing.model.RankingEntry;
import pl.matbos.perform.web.model.gsmrs.Ranking;

public final class RankingConverter {
    private RankingConverter() {
    }

    public static RankingEntry from(Ranking ranking) {
        return new RankingEntry(ranking.rank, ranking.lastRank, ranking.clubName, ranking.totalMatches, ranking.wonMatches, ranking.drawMatches, ranking.lostMatches, ranking.goals, ranking.goalsLost, ranking.points);
    }
}
