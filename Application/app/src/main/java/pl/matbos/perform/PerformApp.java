package pl.matbos.perform;

import android.app.Application;

import pl.matbos.perform.di.AppComponent;
import timber.log.Timber;

public class PerformApp extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        Timber.plant(new Timber.DebugTree());
        super.onCreate();
        appComponent = AppComponent.Injector.create(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}