package io.scottware.fireandice.presentation.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.scottware.fireandice.presentation.FireAndIceApplication;

@Module
public class ApplicationModule {

    private final FireAndIceApplication application;

    public ApplicationModule(FireAndIceApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

}
