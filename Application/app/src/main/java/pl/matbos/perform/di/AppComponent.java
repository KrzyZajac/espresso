package pl.matbos.perform.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import pl.matbos.perform.host.di.HostComponent;
import pl.matbos.perform.host.di.HostModule;
import pl.matbos.perform.news.di.NewsComponent;
import pl.matbos.perform.news.di.NewsModule;
import pl.matbos.perform.scores.di.ScoresComponent;
import pl.matbos.perform.scores.di.ScoresModule;
import pl.matbos.perform.standing.di.StandingsComponent;
import pl.matbos.perform.standing.di.StandingsModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    HostComponent hostComponent(HostModule module);

    NewsComponent newsComponent(NewsModule module);

    ScoresComponent scoresComponent(ScoresModule module);

    StandingsComponent standingsComponent(StandingsModule module);

    class Injector {
        public static AppComponent create(Context applicationContext) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(applicationContext))
                    .build();
        }
    }
}
