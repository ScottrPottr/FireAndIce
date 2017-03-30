package io.scottware.fireandice.data.local;

import io.realm.Realm;

public interface IThreadSafeRealmFactory {

    Realm getRealm(String threadName);

    void close();

}
