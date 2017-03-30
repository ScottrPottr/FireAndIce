package io.scottware.fireandice.presentation.presenter;

import javax.inject.Inject;

import io.scottware.fireandice.data.local.CharacterRealm;
import io.scottware.fireandice.domain.interactor.DefaultInteractorObserver;
import io.scottware.fireandice.domain.interactor.LoadCharacters;
import io.scottware.fireandice.domain.interactor.RefreshData;
import io.scottware.fireandice.presentation.di.PerActivity;
import io.scottware.fireandice.presentation.view.RecyclerWithRefreshView;
import io.scottware.fireandice.presentation.view.adapter.CharacterRecyclerViewAdapter;

@PerActivity
public class CharactersListPresenter implements Presenter<RecyclerWithRefreshView> {

    public static final String INITIAL_DATA_BOOK = "io.scottware.fireandice.initial_data_book";

    private LoadCharacters loadCharacters;
    private RefreshData refreshData;
    private CharacterRealm characterRealm;

    private RecyclerWithRefreshView view;
    private int loadProgress = 0;

    @Inject
    public CharactersListPresenter(LoadCharacters loadCharacters, RefreshData refreshData, CharacterRealm characterRealm) {
        this.loadCharacters = loadCharacters;
        this.refreshData = refreshData;
        this.characterRealm = characterRealm;
    }

    @Override
    public void setView(RecyclerWithRefreshView view) {
        this.view = view;
    }

    @Override
    public void create() {
        String bookUrl = view.getInitialData(INITIAL_DATA_BOOK);
        view.setupRecyclerView(new CharacterRecyclerViewAdapter(characterRealm.getCharacters(bookUrl)));
        view.setupSwipeToRefresh(new Runnable() {
            @Override
            public void run() {
                refreshData.execute(new RefreshDataDisposable(), null);
            }
        });
        view.addOnScrollListener(new RecyclerWithRefreshView.OnScrolled() {
            @Override
            public void onScrolled(int visibleItemCount, int totalItemCount, int firstVisibleItemPosition) {
                int offset = (int) Math.round(visibleItemCount * 1.3);
                if (firstVisibleItemPosition + offset > totalItemCount) {
                    offset = totalItemCount - firstVisibleItemPosition;
                }

                if (firstVisibleItemPosition + offset > loadProgress) {
                    loadCharacters(loadProgress, offset);
                }
            }
        });
    }

    private void loadCharacters(int start, int offset) {
        loadProgress = start + offset;
        loadCharacters.execute(new LoadCharactersDisposable(), LoadCharacters.Params.from(view.getInitialData(INITIAL_DATA_BOOK), start, offset));
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
        loadCharacters.dispose();
    }


    private final class LoadCharactersDisposable extends DefaultInteractorObserver<Long> {

        @Override
        public void onError(Throwable e) {
            //TODO: uh oh
        }

    }

    private final class RefreshDataDisposable extends DefaultInteractorObserver<Long> {

        @Override
        public void onNext(Long value) {
            view.hideSwipeToRefreshProgress();
            loadProgress = 0;
            loadCharacters(loadProgress, 13);
        }

    }

}
