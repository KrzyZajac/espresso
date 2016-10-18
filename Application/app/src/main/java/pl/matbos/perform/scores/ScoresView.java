package pl.matbos.perform.scores;

import pl.matbos.perform.scores.model.ScoreBoard;

public interface ScoresView {
    void setScores(ScoreBoard scoreBoard);
    void showProgress();
    void hideProgress();
    void errorOccured(String errorMessage);
    void showEmpty();
}
