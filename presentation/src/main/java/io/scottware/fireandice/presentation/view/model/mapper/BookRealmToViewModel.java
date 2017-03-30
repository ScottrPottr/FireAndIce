package io.scottware.fireandice.presentation.view.model.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.scottware.fireandice.data.local.model.BookRealmModel;
import io.scottware.fireandice.presentation.view.model.BookViewModel;

@Singleton
public class BookRealmToViewModel {

    @Inject
    public BookRealmToViewModel() {
    }

    public BookViewModel transform(BookRealmModel realmModel) {
        return new BookViewModel(bookUrlToNumber(realmModel.getUrl()), realmModel.getName());
    }

    private String bookUrlToNumber(String bookUrl) {
        String[] split = bookUrl.split("/");
        String lastSplit = split[split.length - 1];
        return "Book " + lastSplit;
    }

}
