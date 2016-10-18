package pl.matbos.perform.standing;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindDimen;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.matbos.perform.R;
import pl.matbos.perform.standing.model.RankingEntry;

public class StandingViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.standings_header_ranking)
    protected TextView ranking;
    @BindView(R.id.standings_header_name)
    protected TextView clubName;
    @BindView(R.id.standings_header_total)
    protected TextView totalMatches;
    @BindView(R.id.standings_header_won)
    protected TextView wonMatches;
    @BindView(R.id.standings_header_draw)
    protected TextView drawMatches;
    @BindView(R.id.standings_header_lost)
    protected TextView lostMatches;
    @BindView(R.id.standings_header_goals_difference)
    protected TextView goalsDifference;
    @BindView(R.id.standings_header_points)
    protected TextView points;
    @BindString(R.string.ranking_format)
    protected String rankingFormat;
    @BindDimen(R.dimen.standing_icon_size)
    protected int iconSize;

    public StandingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    public void bind(RankingEntry entry, int position) {
        itemView.setBackgroundColor(getBackgroundColor(position));
        clubName.setCompoundDrawables(getRankDrawable(entry.rank, entry.lastRank), null, null, null);
        ranking.setText(String.format(rankingFormat, entry.rank));
        clubName.setText(entry.clubName);
        totalMatches.setText(Integer.toString(entry.totalMatches));
        wonMatches.setText(Integer.toString(entry.wonMatches));
        drawMatches.setText(Integer.toString(entry.drawMatches));
        lostMatches.setText(Integer.toString(entry.lostMatches));
        goalsDifference.setText(Integer.toString(entry.goals - entry.goalsLost));
        points.setText(Integer.toString(entry.points));
    }

    private int getBackgroundColor(int position) {
        return itemView.getResources().getColor(position % 2 == 0 ? R.color.standing_blue_background : R.color.standing_white_background);
    }

    private Drawable getRankDrawable(int rank, int lastRank) {
        Drawable drawable = itemView.getResources().getDrawable(getRankDrawableRes(rank, lastRank));
        if (drawable != null) {
            drawable.setBounds(0, 0, iconSize, iconSize);
        }
        return drawable;
    }

    private int getRankDrawableRes(int rank, int lastRank) {
        int diff = rank - lastRank;
        if (diff > 0) {
            return R.drawable.up_arrow;
        } else if (diff == 0) {
            return R.drawable.stop;
        } else {
            return R.drawable.down_arrow;
        }
    }
}