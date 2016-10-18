package pl.matbos.perform.standing;

import java.util.List;

import pl.matbos.perform.standing.model.RankingEntry;

public interface StandingsView {

    void setRanking(List<RankingEntry> ranking);
    void showProgress();
    void hideProgress();
    void errorOccured(String errorMessage);
    void showEmpty();
}