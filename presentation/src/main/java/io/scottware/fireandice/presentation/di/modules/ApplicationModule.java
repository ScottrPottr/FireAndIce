package io.scottware.fireandice.presentation.di.modules;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.scottware.fireandice.data.local.BookRealm;
import io.scottware.fireandice.data.local.IThreadSafeRealmFactory;
import io.scottware.fireandice.data.executor.JobExecutor;
import io.scottware.fireandice.data.local.ThreadSafeRealmFactory;
import io.scottware.fireandice.data.network.IBookService;
import io.scottware.fireandice.data.network.ICharacterService;
import io.scottware.fireandice.data.repository.BooksRepository;
import io.scottware.fireandice.data.repository.CharactersRepository;
import io.scottware.fireandice.data.local.CharacterRealm;
import io.scottware.fireandice.domain.executor.PostExecutionScheduler;
import io.scottware.fireandice.domain.executor.ThreadExecutor;
import io.scottware.fireandice.domain.repositories.IBooksRepository;
import io.scottware.fireandice.domain.repositories.ICharactersRepository;
import io.scottware.fireandice.presentation.ApplicationState;
import io.scottware.fireandice.presentation.FireAndIceApplication;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private final FireAndIceApplication application;
    private final RealmConfiguration realmConfiguration;

    public ApplicationModule(FireAndIceApplication application, RealmConfiguration realmConfiguration) {
        this.application = application;
        this.realmConfiguration = realmConfiguration;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ApplicationState provideApplicationState(IThreadSafeRealmFactory threadSafeRealmFactory) {
        return new ApplicationState(threadSafeRealmFactory);
    }

    @Provides
    @Singleton
    IThreadSafeRealmFactory provideRealmProvider(ThreadSafeRealmFactory threadSafeRealmFactory) {
        return threadSafeRealmFactory;
    }

    @Provides
    @Singleton
    PostExecutionScheduler providePostExecutionScheduler() {
        return new PostExecutionScheduler() {
            @Override
            public Scheduler getScheduler() {
                return AndroidSchedulers.mainThread();
            }
        };
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    IBooksRepository provideBooksRepository(BooksRepository booksRepository) {
        return booksRepository;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://anapioficeandfire.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(new OkHttpClient.Builder()
                        .addNetworkInterceptor(new StethoInterceptor())
                        .build())
                .build();
    }

    @Provides
    @Singleton
    IBookService provideBookService(Retrofit retrofit) {
        return retrofit.create(IBookService.class);
    }

    @Provides
    @Singleton
    ICharacterService provideCharacterService(Retrofit retrofit) {
        return retrofit.create(ICharacterService.class);
    }

    @Provides
    @Singleton
    ICharactersRepository provideCharacterRepository(CharactersRepository charactersRepository) {
        return charactersRepository;
    }

    @Provides
    @Singleton
    RealmConfiguration provideRealmConfigruation() {
        return realmConfiguration;
    }

}
