package pl.matbos.perform.standing;

import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import pl.matbos.perform.R;
import pl.matbos.perform.standing.model.RankingEntry;
import pl.matbos.perform.util.LayoutInflaterCache;

public class StandingsAdapter {
    private final LayoutInflaterCache inflaterCache;

    private List<RankingEntry> ranking = Collections.emptyList();

    public StandingsAdapter(LayoutInflaterCache inflaterCache) {
        this.inflaterCache = inflaterCache;
    }

    public View getHeader(ViewGroup parent) {
        return inflaterCache.getInflater().inflate(R.layout.standings_header, parent, false);
    }

    public View getView(ViewGroup parent, int position) {
        View view = inflaterCache.getInflater().inflate(R.layout.standings_item, parent, false);
        StandingViewHolder standingViewHolder = new StandingViewHolder(view);
        standingViewHolder.bind(ranking.get(position), position);
        return view;
    }

    public int getItemCount() {
        return ranking.size();
    }

    public void setRanking(List<RankingEntry> hotRanking) {
        this.ranking = hotRanking;
    }
}