package pl.matbos.perform.news;

import java.util.List;

import pl.matbos.perform.news.model.News;

public interface NewsView {

    void setNews(List<News> news);
    void showProgress();
    void hideProgress();
    void errorOccured();
    void showEmpty();
    void setTitle(String title);
}
