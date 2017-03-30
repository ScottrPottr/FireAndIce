package io.scottware.fireandice.presentation.di.components;


import android.app.Activity;

import dagger.Component;
import io.scottware.fireandice.presentation.di.PerActivity;
import io.scottware.fireandice.presentation.di.modules.ActivityModule;
import io.scottware.fireandice.presentation.presenter.BookListPresenter;
import io.scottware.fireandice.presentation.presenter.CharactersListPresenter;
import io.scottware.fireandice.presentation.presenter.SplashPresenter;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    // Exposed to sub-graphs
    Activity activity();
    BookListPresenter booksListPresenter();
    CharactersListPresenter charactersListPresenter();
    SplashPresenter splashPresenter();

}
