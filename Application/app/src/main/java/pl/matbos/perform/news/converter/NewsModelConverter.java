package pl.matbos.perform.news.converter;

import pl.matbos.perform.news.model.News;
import pl.matbos.perform.web.model.rss.Article;

public final class NewsModelConverter {
    private NewsModelConverter() {
    }

    public static News from(Article article) {
        return new News(article.guid, article.title, article.description, article.publicationDate, article.imageUrl.url, article.articleUrl, article.category);
    }
}
