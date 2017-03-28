package io.scottware.fireandice.presentation.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.scottware.fireandice.presentation.di.modules.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    //Exposed to sub-graphs
    Context context();

}
