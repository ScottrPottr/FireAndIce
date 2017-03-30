package io.scottware.fireandice.data.local;

import io.realm.Realm;

public abstract class ThreadSafeRealm {

    private IThreadSafeRealmFactory realmProvider;

    public ThreadSafeRealm(IThreadSafeRealmFactory realmProvider) {
        this.realmProvider = realmProvider;
    }

    protected Realm getRealm() {
        return realmProvider.getRealm(Thread.currentThread().getName());
    }

}
