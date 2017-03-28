package io.scottware.fireandice.presentation;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import io.scottware.fireandice.presentation.di.components.ApplicationComponent;
import io.scottware.fireandice.presentation.di.components.DaggerApplicationComponent;
import io.scottware.fireandice.presentation.di.modules.ApplicationModule;

public class FireAndIceApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initialiseInjector();
        initializeLeakDetection();
    }

    private void initialiseInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
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
