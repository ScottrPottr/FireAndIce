package io.scottware.fireandice.data.local;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmResults;
import io.scottware.fireandice.data.local.model.BookRealmModel;

@Singleton
public class BookRealm extends ThreadSafeRealm {

    private OrderedRealmCollection<BookRealmModel> books;

    @Inject
    public BookRealm(IThreadSafeRealmFactory realmProvider) {
        super(realmProvider);
    }

    public void storeBooks(List<BookRealmModel> bookEntities) {
        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(bookEntities);
            }
        });
    }

    public RealmResults<BookRealmModel> getBooks() {
        return getRealm().where(BookRealmModel.class).findAll();
    }

}
