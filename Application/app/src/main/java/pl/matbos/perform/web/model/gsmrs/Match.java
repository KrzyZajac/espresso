package pl.matbos.perform.web.model.gsmrs;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "match")
public class Match {
    @Attribute(name = "team_A_name")
    public final String teamA;
    @Attribute(name = "team_B_name")
    public final String teamB;
    @Attribute(name = "fs_A")
    public final String scoreA;
    @Attribute(name = "fs_B")
    public final String scoreB;

    public Match(@Attribute(name = "team_A_name") String teamA,
                 @Attribute(name = "team_B_name") String teamB,
                 @Attribute(name = "fs_A") String scoreA,
                 @Attribute(name = "fs_B") String scoreB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }
}