package pl.matbos.perform.scores.di;

import android.app.Fragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.matbos.perform.common.ConnectionState;
import pl.matbos.perform.scores.ScoresAdapter;
import pl.matbos.perform.scores.ScoresFragment;
import pl.matbos.perform.scores.ScoresModel;
import pl.matbos.perform.scores.ScoresPresenter;
import pl.matbos.perform.util.LayoutInflaterCache;
import pl.matbos.perform.web.PerformService;
import rx.Observable;

@Module
public class ScoresModule {
    private final ScoresFragment fragment;

    public ScoresModule(ScoresFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    public LayoutInflaterCache providesLayoutInflaterCache() {
        return new LayoutInflaterCache(fragment.getActivity());
    }


    @Provides
    @Singleton
    public ScoresPresenter providesPresenter(ScoresModel model, Observable<ConnectionState> connectionState) {
        return new ScoresPresenter(fragment, model,connectionState);
    }

    @Provides
    @Singleton
    public ScoresModel providesModel(PerformService service) {
        return new ScoresModel(service);
    }

    @Provides
    public ScoresAdapter providesScoresAdapter(LayoutInflaterCache inflaterCache) {
        return new ScoresAdapter(inflaterCache);
    }
}