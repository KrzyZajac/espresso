package pl.matbos.perform.scores;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.matbos.perform.R;
import pl.matbos.perform.scores.model.Score;

public class ScoreViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.score_view_score)
    protected TextView scoreView;
    @BindView(R.id.score_view_teamA)
    protected TextView teamA;
    @BindView(R.id.score_view_teamB)
    protected TextView teamB;
    @BindString(R.string.score_format)
    protected String scoreFormat;

    public ScoreViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Score score) {
        teamA.setText(score.teamA);
        teamB.setText(score.teamB);
        scoreView.setText(String.format(scoreFormat, score.scoreA, score.scoreB));
    }
}
