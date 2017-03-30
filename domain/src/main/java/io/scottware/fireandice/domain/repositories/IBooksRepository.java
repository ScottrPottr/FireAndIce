package io.scottware.fireandice.domain.repositories;

import io.reactivex.Observable;

public interface IBooksRepository {

    Observable<Long> loadBooks();

}
