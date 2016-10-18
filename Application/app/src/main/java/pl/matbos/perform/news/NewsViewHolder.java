package pl.matbos.perform.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.matbos.perform.R;
import pl.matbos.perform.news.model.News;
import pl.matbos.perform.news_details.NewsDetailsActivity;
import pl.matbos.perform.util.DateUtils;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.news_item_date)
    protected TextView dateView;
    @BindView(R.id.news_item_description)
    protected TextView descriptionView;
    @BindView(R.id.news_item_image)
    protected ImageView image;

    public NewsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(News news) {
        descriptionView.setText(news.description);
        dateView.setText(DateUtils.DISPLAY_DATE_FORMATTER.print(news.publicationDate));
        loadNewsImage(news.imageUrl.trim(), image);
        itemView.setOnClickListener(view -> itemClicked(view, news.articleUrl));
    }

    private void loadNewsImage(String imageUrl, ImageView view) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .fit()
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_error)
                .centerInside()
                .into(view);
    }

    private void itemClicked(View view, String url) {
        view.getContext().startActivity(NewsDetailsActivity.getIntent(view.getContext(), url));
    }
}
