package pl.matbos.perform.standing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.matbos.perform.R;
import pl.matbos.perform.host.StateShow;
import pl.matbos.perform.standing.di.StandingsComponent;
import pl.matbos.perform.standing.model.RankingEntry;

public class StandingsFragment extends Fragment implements StandingsView {
    @BindView(R.id.standings_table)
    protected TableLayout table;

    @Inject
    protected StandingsPresenter presenter;
    @Inject
    protected StandingsAdapter adapter;

    private StateShow stateShow = StateShow.EMPTY;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StandingsComponent.Injector.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getResources().getString(R.string.standings));
        presenter.viewReady();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.viewPaused();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stanings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    public void setStateShow(StateShow stateShow) {
        this.stateShow = stateShow;
    }

    @Override
    public void setRanking(List<RankingEntry> ranking) {
        adapter.setRanking(ranking);
        layoutChildren();
    }

    private void layoutChildren() {
        table.removeAllViews();
        table.addView(adapter.getHeader(table));
        for (int i = 0; i < adapter.getItemCount(); ++i) {
            table.addView(adapter.getView(table, i));
        }
        table.invalidate();
    }

    @Override
    public void showProgress() {
        stateShow.showProgress();
    }

    @Override
    public void hideProgress() {
        getActivity().runOnUiThread(() -> stateShow.hideProgress());
    }

    @Override
    public void errorOccured(String errorMessage) {
        stateShow.errorOccured(errorMessage);
    }

    @Override
    public void showEmpty() {
        stateShow.showEmpty(getString(R.string.no_standings));
    }
}