package pl.matbos.perform.scores;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;
import pl.matbos.perform.R;
import pl.matbos.perform.host.StateShow;
import pl.matbos.perform.scores.di.ScoresComponent;
import pl.matbos.perform.scores.model.ScoreBoard;

public class ScoresFragment extends Fragment implements ScoresView {

    @BindView(R.id.scores_recycler)
    protected RecyclerView scoresRecycler;
    @Inject
    protected ScoresPresenter presenter;
    @Inject
    protected ScoresAdapter adapter;

    private StateShow stateShow = StateShow.EMPTY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScoresComponent.Injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scores, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupRecycler();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getResources().getString(R.string.scores));
        presenter.viewReady();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.viewPaused();
    }

    private void setupRecycler() {
        scoresRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        scoresRecycler.setAdapter(adapter);
        scoresRecycler.setItemAnimator(new SlideInRightAnimator());
    }

    public void setStateShow(@NonNull StateShow stateShow) {
        this.stateShow = stateShow != null ? stateShow : StateShow.EMPTY;
    }

    public void setScores(ScoreBoard scoreBoard) {
        adapter.setScores(scoreBoard);
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
        stateShow.showEmpty(getString(R.string.no_scores));
    }
}