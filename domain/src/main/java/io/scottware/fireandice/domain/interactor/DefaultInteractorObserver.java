package io.scottware.fireandice.domain.interactor;

import io.reactivex.observers.DisposableObserver;

public class DefaultInteractorObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T value) {
        // no-op by default
    }

    @Override
    public void onError(Throwable e) {
        // no-op by default
    }

    @Override
    public void onComplete() {
        // no-op by default
    }
}
