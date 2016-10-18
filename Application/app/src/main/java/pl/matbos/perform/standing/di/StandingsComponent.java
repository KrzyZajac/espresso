package pl.matbos.perform.standing.di;

import javax.inject.Singleton;

import dagger.Subcomponent;
import pl.matbos.perform.PerformApp;
import pl.matbos.perform.standing.StandingsFragment;

@Singleton
@Subcomponent(modules = StandingsModule.class)
public interface StandingsComponent {

    void inject(StandingsFragment fragment);

    class Injector {
        public static void inject(StandingsFragment fragment) {
            PerformApp.getAppComponent()
                    .standingsComponent(new StandingsModule(fragment))
                    .inject(fragment);
        }
    }
}