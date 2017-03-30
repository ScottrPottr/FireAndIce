package io.scottware.fireandice.presentation.view.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;

import io.scottware.fireandice.presentation.R;
import io.scottware.fireandice.presentation.presenter.Presenter;
import io.scottware.fireandice.presentation.view.IView;
import io.scottware.fireandice.presentation.view.activity.base.BasePresenterActivity;

public class SplashActivity extends BasePresenterActivity<IView> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getPresenter().setView(this);
        getPresenter().create();
    }

    @Override
    protected Presenter<IView> getPresenter() {
        return getActivityComponent().splashPresenter();
    }

}
