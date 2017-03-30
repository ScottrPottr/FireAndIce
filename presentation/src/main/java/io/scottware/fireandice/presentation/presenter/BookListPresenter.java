package io.scottware.fireandice.presentation.presenter;

import android.app.Activity;

import javax.inject.Inject;

import io.scottware.fireandice.data.local.BookRealm;
import io.scottware.fireandice.data.local.model.BookRealmModel;
import io.scottware.fireandice.domain.interactor.DefaultInteractorObserver;
import io.scottware.fireandice.domain.interactor.RefreshData;
import io.scottware.fireandice.presentation.di.PerActivity;
import io.scottware.fireandice.presentation.navigation.Navigator;
import io.scottware.fireandice.presentation.view.RecyclerWithRefreshView;
import io.scottware.fireandice.presentation.view.adapter.BookRecyclerViewAdapter;
import io.scottware.fireandice.presentation.view.model.mapper.BookRealmToViewModel;

@PerActivity
public class BookListPresenter implements Presenter<RecyclerWithRefreshView>, BookRecyclerViewAdapter.OnItemClickListener {

    private Activity activity;
    private BookRealm bookRealm;
    private Navigator navigator;
    private RefreshData refreshData;
    private BookRealmToViewModel bookRealmToViewModel;
    private RecyclerWithRefreshView view;

    @Inject
    public BookListPresenter(Activity activity, BookRealm bookRealm, Navigator navigator, RefreshData refreshData, BookRealmToViewModel bookRealmToViewModel) {
        this.activity = activity;
        this.bookRealm = bookRealm;
        this.navigator = navigator;
        this.refreshData = refreshData;
        this.bookRealmToViewModel = bookRealmToViewModel;
    }

    public void setView(RecyclerWithRefreshView view) {
        this.view = view;
    }

    @Override
    public void create() {
        view.setupRecyclerView(new BookRecyclerViewAdapter(bookRealm.getBooks(), this, bookRealmToViewModel));
        view.setupSwipeToRefresh(new Runnable() {
            @Override
            public void run() {
                refreshData.execute(new RefreshDataDisposable(), null);
            }
        });
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        view = null;
        refreshData.dispose();
    }

    @Override
    public void onItemClick(BookRealmModel bookRealmModel) {
        navigator.navigateToCharactersList(activity, bookRealmModel.getUrl());
    }

    private final class RefreshDataDisposable extends DefaultInteractorObserver<Long> {

        @Override
        public void onNext(Long value) {
            view.hideSwipeToRefreshProgress();
        }

    }

}
