package pl.matbos.perform.scores.model;

public class Score {
    public final String teamA;
    public final String teamB;
    public final String scoreA;
    public final String scoreB;

    public Score(String teamA, String teamB, String scoreA, String scoreB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }
}