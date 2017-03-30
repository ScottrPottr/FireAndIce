package io.scottware.fireandice.domain.interactor;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.scottware.fireandice.domain.executor.PostExecutionScheduler;
import io.scottware.fireandice.domain.executor.ThreadExecutor;
import io.scottware.fireandice.domain.repositories.IBooksRepository;

public class LoadBooksWithMinimumDelay extends Interactor<Long, Long> {

    private IBooksRepository booksRepository;

    @Inject
    LoadBooksWithMinimumDelay(ThreadExecutor threadExecutor, PostExecutionScheduler postExecutionScheduler, IBooksRepository booksRepository) {
        super(threadExecutor, postExecutionScheduler);
        this.booksRepository = booksRepository;
    }

    @Override
    Observable<Long> buildUseCaseObservable(Long minimumDelayInMillis) {
        Observable<Long> timer = Observable.timer(minimumDelayInMillis, TimeUnit.MILLISECONDS);
        return booksRepository.loadBooks().zipWith(timer, new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(Long aVoid, Long aLong) throws Exception {
                return 0L;
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
