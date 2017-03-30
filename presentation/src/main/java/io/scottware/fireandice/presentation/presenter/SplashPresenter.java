package io.scottware.fireandice.presentation.presenter;

import javax.inject.Inject;

import io.scottware.fireandice.domain.interactor.DefaultInteractorObserver;
import io.scottware.fireandice.domain.interactor.LoadBooksWithMinimumDelay;
import io.scottware.fireandice.presentation.ApplicationState;
import io.scottware.fireandice.presentation.di.PerActivity;
import io.scottware.fireandice.presentation.view.IView;

@PerActivity
public class SplashPresenter implements Presenter<IView> {

    private LoadBooksWithMinimumDelay loadBooksWithMinimumDelay;
    private ApplicationState applicationState;
    private IView view;

    @Inject
    public SplashPresenter(LoadBooksWithMinimumDelay loadBooksWithMinimumDelay, ApplicationState applicationState) {
        this.loadBooksWithMinimumDelay = loadBooksWithMinimumDelay;
        this.applicationState = applicationState;
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void create() {

    }


    @Override
    public void resume() {
        loadBooksWithMinimumDelay.execute(new LoadInitialDataObserver(), 5000L);
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        view = null;
        loadBooksWithMinimumDelay.dispose();
    }

    private final class LoadInitialDataObserver extends DefaultInteractorObserver<Long> {

        @Override
        public void onError(Throwable e) {
            view.toast("Sorry error loading, please check back soon.");
        }

        @Override
        public void onNext(Long value) {
            applicationState.setIsLoadedInitialData(true);
            view.end();
        }
    }
}
