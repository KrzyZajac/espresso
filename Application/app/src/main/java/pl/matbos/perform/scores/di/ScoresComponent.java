package pl.matbos.perform.scores.di;

import javax.inject.Singleton;

import dagger.Subcomponent;
import pl.matbos.perform.PerformApp;
import pl.matbos.perform.scores.ScoresFragment;

@Singleton
@Subcomponent(modules = ScoresModule.class)
public interface ScoresComponent {

    void inject(ScoresFragment fragment);

    class Injector {
        public static void inject(ScoresFragment fragment) {
            PerformApp.getAppComponent()
                    .scoresComponent(new ScoresModule(fragment))
                    .inject(fragment);
        }
    }
}