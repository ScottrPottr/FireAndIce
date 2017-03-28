package io.scottware.fireandice.presentation.di.components;


import android.app.Activity;

import dagger.Component;
import io.scottware.fireandice.presentation.di.PerActivity;
import io.scottware.fireandice.presentation.di.modules.ActivityModule;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    // Exposed to sub-graphs
    Activity activity();

}
