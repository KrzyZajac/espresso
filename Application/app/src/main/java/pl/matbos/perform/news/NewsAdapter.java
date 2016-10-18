package pl.matbos.perform.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.matbos.perform.R;
import pl.matbos.perform.news.model.News;
import pl.matbos.perform.util.LayoutInflaterCache;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private final LayoutInflaterCache inflaterCache;
    private List<News> news = new ArrayList<>();

    public NewsAdapter(LayoutInflaterCache inflaterCache) {
        this.inflaterCache = inflaterCache;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflaterCache.getInflater().inflate(R.layout.news_view, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public News getItem(int position) {
        return news.get(position);
    }

    public void setNews(List<News> hotNews) {
        boolean wasEmpty = news.isEmpty();
        news.clear();
        news.addAll(hotNews);
        if (!wasEmpty) {
            notifyItemRangeRemoved(0, news.size());
        }
        notifyItemRangeInserted(0, news.size());
    }
}