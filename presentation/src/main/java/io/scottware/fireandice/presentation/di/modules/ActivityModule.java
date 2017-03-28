package io.scottware.fireandice.presentation.di.modules;


import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import io.scottware.fireandice.presentation.di.PerActivity;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }

}
