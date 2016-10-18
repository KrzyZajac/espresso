package pl.matbos.perform.news.di;

import javax.inject.Singleton;

import dagger.Subcomponent;
import pl.matbos.perform.PerformApp;
import pl.matbos.perform.news.NewsFragment;

@Singleton
@Subcomponent(modules = NewsModule.class)
public interface NewsComponent {

    void inject(NewsFragment activity);

    class Injector {
        public static void inject(NewsFragment fragment) {
            PerformApp.getAppComponent()
                    .newsComponent(new NewsModule(fragment))
                    .inject(fragment);
        }
    }
}