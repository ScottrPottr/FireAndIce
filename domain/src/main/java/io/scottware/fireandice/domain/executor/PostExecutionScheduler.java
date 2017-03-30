package io.scottware.fireandice.domain.executor;

import io.reactivex.Scheduler;

public interface PostExecutionScheduler {
    Scheduler getScheduler();
}
