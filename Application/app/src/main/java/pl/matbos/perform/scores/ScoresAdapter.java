package pl.matbos.perform.scores;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.matbos.perform.R;
import pl.matbos.perform.scores.model.ScoreBoard;
import pl.matbos.perform.util.LayoutInflaterCache;

public class ScoresAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_SCORE = 1;
    private final LayoutInflaterCache inflaterCache;
    private ScoreBoard scoreBoard;

    public ScoresAdapter(LayoutInflaterCache inflaterCache) {
        this.inflaterCache = inflaterCache;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_SCORE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_HEADER) {
            return new ScoreHeaderViewHolder((TextView) inflaterCache.getInflater().inflate(R.layout.score_header, parent, false));
        } else {
            return new ScoreViewHolder(inflaterCache.getInflater().inflate(R.layout.score_item_view, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == VIEW_TYPE_HEADER) {
            ((ScoreHeaderViewHolder) holder).bind(scoreBoard.date);
        } else {
            ((ScoreViewHolder) holder).bind(scoreBoard.scores.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return scoreBoard == null ? 0 : scoreBoard.scores.size() + 1;
    }

    public void setScores(ScoreBoard scoreBoard) {
        int oldCount = getItemCount();
        this.scoreBoard = scoreBoard;
        if (oldCount != 0) {
            notifyItemRangeRemoved(0, oldCount);
        }
        notifyItemRangeInserted(0, getItemCount());
    }
}