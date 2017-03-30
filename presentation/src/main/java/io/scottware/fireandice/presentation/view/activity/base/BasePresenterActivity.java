package io.scottware.fireandice.presentation.view.activity.base;

import android.widget.Toast;

import io.scottware.fireandice.presentation.presenter.Presenter;
import io.scottware.fireandice.presentation.view.IView;

public abstract class BasePresenterActivity<View> extends BaseActivity implements IView {

    protected abstract Presenter<View> getPresenter();

    @Override
    public String getInitialData(String key) {
        return getIntent().getStringExtra(key);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }

    @Override
    public void end() {
        finish();
    }

    @Override
    public void toast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
    }

}
