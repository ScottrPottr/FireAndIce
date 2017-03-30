package io.scottware.fireandice.presentation.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.scottware.fireandice.data.local.BookRealm;
import io.scottware.fireandice.data.local.CharacterRealm;
import io.scottware.fireandice.data.local.IThreadSafeRealmFactory;
import io.scottware.fireandice.data.network.IBookService;
import io.scottware.fireandice.domain.executor.PostExecutionScheduler;
import io.scottware.fireandice.domain.executor.ThreadExecutor;
import io.scottware.fireandice.domain.repositories.IBooksRepository;
import io.scottware.fireandice.domain.repositories.ICharactersRepository;
import io.scottware.fireandice.presentation.ApplicationState;
import io.scottware.fireandice.presentation.di.modules.ApplicationModule;
import io.scottware.fireandice.presentation.navigation.Navigator;
import io.scottware.fireandice.presentation.view.activity.base.BaseActivity;
import io.scottware.fireandice.presentation.view.model.mapper.BookRealmToViewModel;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs
    Context context();
    ApplicationState applicationState();
    ThreadExecutor threadExecutor();
    PostExecutionScheduler postExecutionScheduler();
    IThreadSafeRealmFactory threadSafeRealmFactory();
    Navigator navigator();
    IBooksRepository booksRepository();
    ICharactersRepository charactersRepository();
    IBookService booksService();
    BookRealmToViewModel bookRealmToViewModel();
    BookRealm bookRealm();
    CharacterRealm characterRealm();

}
