package io.scottware.fireandice.presentation;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.scottware.fireandice.presentation.di.components.ApplicationComponent;
import io.scottware.fireandice.presentation.di.components.DaggerApplicationComponent;
import io.scottware.fireandice.presentation.di.modules.ApplicationModule;

public class FireAndIceApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        initializeLeakDetection();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
//                .initialData(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        BookRealmModel book = realm.createObject(BookRealmModel.class, "Book 1");
//                        book.setName("A Game of Thrones");
//
//                        BookRealmModel book2 = realm.createObject(BookRealmModel.class, "Book 2");
//                        book2.setName("A Clash of Kings");
//
//                        BookRealmModel book3 = realm.createObject(BookRealmModel.class, "Book 3");
//                        book3.setName("A Storm of Swords");
//                    }
//                })
                .build();
        Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);
        this.initialiseInjector(realmConfig);
    }

    private void initialiseInjector(RealmConfiguration realmConfiguration) {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, realmConfiguration))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

}
