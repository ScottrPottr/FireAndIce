package io.scottware.fireandice.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.scottware.fireandice.domain.executor.PostExecutionScheduler;
import io.scottware.fireandice.domain.executor.ThreadExecutor;
import io.scottware.fireandice.domain.repositories.IBooksRepository;

public class RefreshData extends Interactor<Long, Void> {

    private IBooksRepository booksRepository;

    @Inject
    RefreshData(ThreadExecutor threadExecutor, PostExecutionScheduler postExecutionScheduler, IBooksRepository booksRepository) {
        super(threadExecutor, postExecutionScheduler);
        this.booksRepository = booksRepository;
    }

    @Override
    Observable<Long> buildUseCaseObservable(Void aVoid) {
        return booksRepository.loadBooks();
    }

}
