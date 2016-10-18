package pl.matbos.perform.web.model.gsmrs;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "ranking")
public class Ranking {
    @Attribute(name = "rank")
    public final Integer rank;
    @Attribute(name = "last_rank")
    public final Integer lastRank;
    @Attribute(name = "club_name")
    public final String clubName;
    @Attribute(name = "matches_total")
    public final Integer totalMatches;
    @Attribute(name = "matches_won")
    public final Integer wonMatches;
    @Attribute(name = "matches_draw")
    public final Integer drawMatches;
    @Attribute(name = "matches_lost")
    public final Integer lostMatches;
    @Attribute(name = "goals_pro")
    public final Integer goals;
    @Attribute(name = "goals_against")
    public final Integer goalsLost;
    @Attribute(name = "points")
    public final Integer points;

    public Ranking(@Attribute(name = "rank") Integer rank,
                   @Attribute(name = "last_rank") Integer lastRank,
                   @Attribute(name = "club_name") String clubName,
                   @Attribute(name = "matches_total") Integer totalMatches,
                   @Attribute(name = "matches_won") Integer wonMatches,
                   @Attribute(name = "matches_draw") Integer drawMatches,
                   @Attribute(name = "matches_lost") Integer lostMatches,
                   @Attribute(name = "goals_pro") Integer goals,
                   @Attribute(name = "goals_against") Integer goalsLost,
                   @Attribute(name = "points") Integer points) {
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
}
