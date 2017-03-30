package io.scottware.fireandice.data.local;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

@Singleton
public class ThreadSafeRealmFactory implements IThreadSafeRealmFactory {

    private Map<String, Realm> realmPool;
    private RealmConfiguration realmConfiguration;

    @Inject
    public ThreadSafeRealmFactory(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
        realmPool = new HashMap<>();
    }

    @Override
    public synchronized Realm getRealm(String threadName) {
        Realm realm = realmPool.get(threadName);
        if (realm != null) {
            return realm;
        }

        Realm newRealm = Realm.getInstance(realmConfiguration);
        realmPool.put(threadName, newRealm);
        return newRealm;
    }

    @Override
    public synchronized void close() {
        for (Realm realm : realmPool.values()) {
            realm.close();
        }
        realmPool = new HashMap<>();
    }

}
