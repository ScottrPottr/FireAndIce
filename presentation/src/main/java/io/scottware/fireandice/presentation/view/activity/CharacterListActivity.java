package io.scottware.fireandice.presentation.view.activity;

import io.scottware.fireandice.presentation.presenter.Presenter;
import io.scottware.fireandice.presentation.view.RecyclerWithRefreshView;
import io.scottware.fireandice.presentation.view.activity.base.RecyclerViewWithRefreshActivity;

public class CharacterListActivity extends RecyclerViewWithRefreshActivity implements RecyclerWithRefreshView {

    @Override
    protected Presenter<RecyclerWithRefreshView> getPresenter() {
        return getActivityComponent().charactersListPresenter();
    }

}
