package pl.matbos.perform.news;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;
import pl.matbos.perform.R;
import pl.matbos.perform.common.recycler.SimpleSeparator;
import pl.matbos.perform.host.StateShow;
import pl.matbos.perform.news.di.NewsComponent;
import pl.matbos.perform.news.model.News;

public class NewsFragment extends Fragment implements NewsView {

    @BindView(R.id.news_recycler)
    protected RecyclerView recyclerView;
    @BindColor(R.color.news_separator)
    protected int color;
    @BindDimen(R.dimen.news_separator_height)
    protected int height;

    @Inject
    protected NewsAdapter newsAdapter;
    @Inject
    protected NewsPresenter newsPresenter;

    private StateShow stateShow = StateShow.EMPTY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewsComponent.Injector.inject(this);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getActivity().setTitle(context.getString(R.string.news));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.news_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_news_refresh) {
            newsPresenter.reloadRequested();
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecycler();
    }

    @Override
    public void onResume() {
        super.onResume();
        newsPresenter.viewReady();
    }

    @Override
    public void onPause() {
        super.onPause();
        newsPresenter.viewPaused();
    }

    public void setStateShow(@NonNull StateShow stateShow) {
        //noinspection ConstantConditions
        this.stateShow = stateShow != null ? stateShow : StateShow.EMPTY;
    }

    private void setupRecycler() {
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setItemAnimator(new SlideInRightAnimator());
        recyclerView.addItemDecoration(new SimpleSeparator(color, height));
    }

    @Override
    public void setNews(List<News> news) {
        stateShow.hideNotifications();
        newsAdapter.setNews(news);
    }

    @Override
    public void showProgress() {
        getActivity().runOnUiThread(() -> stateShow.showProgress());
    }

    @Override
    public void hideProgress() {
        stateShow.hideProgress();
    }

    @Override
    public void errorOccured() {
        stateShow.errorOccured(getResources().getString(R.string.problems_reaching_server));
    }

    @Override
    public void showEmpty() {
        stateShow.showEmpty(getString(R.string.no_news));
    }

    @Override
    public void setTitle(String title) {
        getActivity().setTitle(title);
    }
}