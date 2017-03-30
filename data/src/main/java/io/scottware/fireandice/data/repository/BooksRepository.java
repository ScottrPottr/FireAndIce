package io.scottware.fireandice.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.scottware.fireandice.data.local.BookRealm;
import io.scottware.fireandice.data.mapper.BookNetworkToRealmMapper;
import io.scottware.fireandice.data.network.IBookService;
import io.scottware.fireandice.data.network.model.BookNetworkModel;
import io.scottware.fireandice.domain.repositories.IBooksRepository;

@Singleton
public class BooksRepository implements IBooksRepository {

    private IBookService bookService;
    private BookRealm bookRealm;
    private BookNetworkToRealmMapper bookNetworkToRealmMapper;

    @Inject
    public BooksRepository(IBookService bookService, BookRealm bookRealm, BookNetworkToRealmMapper bookNetworkToRealmMapper) {
        this.bookService = bookService;
        this.bookRealm = bookRealm;
        this.bookNetworkToRealmMapper = bookNetworkToRealmMapper;
    }

    @Override
    public Observable<Long> loadBooks() {
        return bookService.getBooks().map(new Function<List<BookNetworkModel>, Long>() {
            @Override
            public Long apply(List<BookNetworkModel> booksResponse) throws Exception {
                bookRealm.storeBooks(bookNetworkToRealmMapper.transform(booksResponse));
                return 0L;
            }
        });
    }

}
