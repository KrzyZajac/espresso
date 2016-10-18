package pl.matbos.perform.host.di;

import dagger.Subcomponent;
import pl.matbos.perform.PerformApp;
import pl.matbos.perform.host.HostActivity;

@Subcomponent(modules = HostModule.class)
public interface HostComponent {

    void inject(HostActivity activity);

    class Injector {
        public static void inject(HostActivity activity) {
            PerformApp.getAppComponent()
                    .hostComponent(new HostModule(activity))
                    .inject(activity);
        }
    }
}