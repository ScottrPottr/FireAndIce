package io.scottware.fireandice.domain.interactor;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.scottware.fireandice.domain.executor.PostExecutionScheduler;
import io.scottware.fireandice.domain.executor.ThreadExecutor;
import io.scottware.fireandice.domain.repositories.IBooksRepository;
import io.scottware.fireandice.domain.repositories.ICharactersRepository;

public class LoadCharacters extends Interactor<Long, LoadCharacters.Params> {

    private IBooksRepository booksRepository;
    private ICharactersRepository charactersRepository;

    @Inject
    LoadCharacters(ThreadExecutor threadExecutor, PostExecutionScheduler postExecutionScheduler,
                   IBooksRepository booksRepository, ICharactersRepository charactersRepository) {
        super(threadExecutor, postExecutionScheduler);
        this.booksRepository = booksRepository;
        this.charactersRepository = charactersRepository;
    }

    @Override
    Observable<Long> buildUseCaseObservable(Params params) {
        return charactersRepository.loadCharacters(params.bookUrl, params.start, params.offset);
    }

    public static final class Params {

        private String bookUrl;
        private final int start;
        private final int offset;

        private Params(String bookUrl, int start, int offset) {
            this.bookUrl = bookUrl;
            this.start = start;
            this.offset = offset;
        }

        public static Params from(String bookUrl, int start, int offset) {
            return new Params(bookUrl, start, offset);
        }

    }

}
