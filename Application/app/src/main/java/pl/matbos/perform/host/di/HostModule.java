package pl.matbos.perform.host.di;

import dagger.Module;
import dagger.Provides;
import pl.matbos.perform.common.ConnectionState;
import pl.matbos.perform.host.HostPresenter;
import pl.matbos.perform.host.HostView;
import rx.Observable;

@Module
public class HostModule {
    private final HostView hostView;

    public HostModule(HostView view) {
        hostView = view;
    }

    @Provides
    public HostPresenter providesPresenter(Observable<ConnectionState> connectionState) {
        return new HostPresenter(hostView, connectionState);
    }
}