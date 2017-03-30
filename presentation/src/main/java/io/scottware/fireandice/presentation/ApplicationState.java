package io.scottware.fireandice.presentation;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.scottware.fireandice.data.local.IThreadSafeRealmFactory;

@Singleton
public class ApplicationState {

    private IThreadSafeRealmFactory threadSafeRealmFactory;
    private int activityCount = 0;
    private boolean hasLoadedInitialData;

    @Inject
    public ApplicationState(IThreadSafeRealmFactory threadSafeRealmFactory) {
        this.threadSafeRealmFactory = threadSafeRealmFactory;
    }

    public void incrementActivityCount() {
        activityCount++;
    }

    public void decrementActivityCount() {
        activityCount--;
        if (activityCount == 0) {
            threadSafeRealmFactory.close();
        }
    }

    public boolean isLoadedInitialData() {
        return hasLoadedInitialData;
    }

    public void setIsLoadedInitialData(boolean hasLoadedInitialData) {
        this.hasLoadedInitialData = hasLoadedInitialData;
    }

}
