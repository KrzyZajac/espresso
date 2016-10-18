package pl.matbos.perform.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.laimiux.rxnetwork.RxNetwork;

import org.joda.time.DateTime;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import pl.matbos.perform.common.ConnectionState;
import pl.matbos.perform.web.PerformService;
import pl.matbos.perform.web.converter.DateTimeElementConverter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import rx.Observable;
import rx.subjects.BehaviorSubject;

@Module
public class AppModule {

    private final Context applicationContext;

    public AppModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    @Singleton
    public PerformService providesJitenshaService(OkHttpClient okHttpClient,
                                                  SimpleXmlConverterFactory simpleXmlConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(PerformService.ENDPOINT)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(simpleXmlConverterFactory)
                .build()
                .create(PerformService.class);
    }

    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }

    @Provides
    @Singleton
    public SimpleXmlConverterFactory providesSimpleXmlConverterFactory(Persister persister) {
        return SimpleXmlConverterFactory.createNonStrict(persister);
    }

    @Provides
    public Persister providesSimpleXmlPersister() {
        try {
            Registry registry = new Registry();
            registry.bind(DateTime.class, DateTimeElementConverter.class);
            RegistryStrategy strategy = new RegistryStrategy(registry);
            return new Persister(strategy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Provides
    @Singleton
    public Observable<ConnectionState> providesInternetConnectionObservable() {
        BehaviorSubject<ConnectionState> subject = BehaviorSubject.create();
        getConnectionStateObservable(applicationContext).subscribe(subject);
        return subject.asObservable();
    }

    @NonNull
    private Observable<ConnectionState> getConnectionStateObservable(Context context) {
        return RxNetwork.stream(context)
                .map(state -> state ? ConnectionState.CONNECTED : ConnectionState.NOT_CONNECTED);
    }
}