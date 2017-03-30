package io.scottware.fireandice.domain.repositories;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface ICharactersRepository {

    Observable<Long> loadCharacters(List<String> characterUrl);

    Observable<Long> loadCharacters(String bookUrl, int start, int offset);
}
