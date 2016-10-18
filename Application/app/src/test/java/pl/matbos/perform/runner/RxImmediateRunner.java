package pl.matbos.perform.runner;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaTestPlugins;
import rx.schedulers.Schedulers;

/**
 * This runner allows me to test the application without exposing
 * Observables and using TestSubscriber.
 */
public class RxImmediateRunner extends BlockJUnit4ClassRunner {
    public RxImmediateRunner(Class<?> klass) throws InitializationError {
        super(klass);

        RxJavaTestPlugins.resetPlugins();
        RxJavaTestPlugins.allImmediateSchedulers();

        RxAndroidPlugins.getInstance().reset();
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }
}
