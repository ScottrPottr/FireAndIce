package io.scottware.fireandice.presentation.view.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import io.scottware.fireandice.presentation.ApplicationState;
import io.scottware.fireandice.presentation.FireAndIceApplication;
import io.scottware.fireandice.presentation.di.components.ActivityComponent;
import io.scottware.fireandice.presentation.di.components.ApplicationComponent;
import io.scottware.fireandice.presentation.di.components.DaggerActivityComponent;
import io.scottware.fireandice.presentation.di.modules.ActivityModule;
import io.scottware.fireandice.presentation.navigation.Navigator;


public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    ApplicationState applicationState;

    @Inject
    Navigator navigator;

    ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
        applicationState.incrementActivityCount();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!applicationState.isLoadedInitialData()) {
            navigator.navigateToSplashScreen(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        applicationState.decrementActivityCount();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((FireAndIceApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return activityComponent;
    }

}
