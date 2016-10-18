package pl.matbos.perform.standing.model;

public class RankingEntry implements Comparable {
    public final int rank;
    public final int lastRank;
    public final String clubName;
    public final int totalMatches;
    public final int wonMatches;
    public final int drawMatches;
    public final int lostMatches;
    public final int goals;
    public final int goalsLost;
    public final int points;

    public RankingEntry(int rank, int lastRank, String clubName, int totalMatches, int wonMatches, int drawMatches, int lostMatches, int goals, int goalsLost, int points) {
        this.rank = rank;
        this.lastRank = lastRank;
        this.clubName = clubName;
        this.totalMatches = totalMatches;
        this.wonMatches = wonMatches;
        this.drawMatches = drawMatches;
        this.lostMatches = lostMatches;
        this.goals = goals;
        this.goalsLost = goalsLost;
        this.points = points;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof RankingEntry) {
            RankingEntry other = (RankingEntry) o;
            return rank - other.rank;
        }
        return 0;
    }
}
