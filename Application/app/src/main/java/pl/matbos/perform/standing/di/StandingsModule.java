package pl.matbos.perform.standing.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.matbos.perform.common.ConnectionState;
import pl.matbos.perform.standing.StandingsAdapter;
import pl.matbos.perform.standing.StandingsFragment;
import pl.matbos.perform.standing.StandingsModel;
import pl.matbos.perform.standing.StandingsPresenter;
import pl.matbos.perform.util.LayoutInflaterCache;
import pl.matbos.perform.web.PerformService;
import rx.Observable;

@Module
public class StandingsModule {

    private final StandingsFragment fragment;

    public StandingsModule(StandingsFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    public LayoutInflaterCache providesLayoutInflaterCache() {
        return new LayoutInflaterCache(fragment.getActivity());
    }

    @Provides
    @Singleton
    public StandingsPresenter providesPresenter(StandingsModel model, Observable<ConnectionState> connectionState) {
        return new StandingsPresenter(fragment, model, connectionState);
    }

    @Provides
    @Singleton
    public StandingsModel providesModel(PerformService service) {
        return new StandingsModel(service);
    }

    @Provides
    public StandingsAdapter providesAdapter(LayoutInflaterCache inflaterCache) {
        return new StandingsAdapter(inflaterCache);
    }
}