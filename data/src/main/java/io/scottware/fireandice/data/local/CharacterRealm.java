package io.scottware.fireandice.data.local;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmList;
import io.scottware.fireandice.data.local.model.BookRealmModel;
import io.scottware.fireandice.data.local.model.CharacterRealmModel;

public class CharacterRealm extends ThreadSafeRealm {

    @Inject
    public CharacterRealm(IThreadSafeRealmFactory realmProvider) {
        super(realmProvider);
    }

    public void storeCharacter(CharacterRealmModel characterRealmModel) {
        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(characterRealmModel);
            }
        });
    }

    public RealmList<CharacterRealmModel> getCharacters(String bookUrl) {
        return getRealm().where(BookRealmModel.class)
                .equalTo("url", bookUrl)
                .findFirst()
                .getCharacters();
    }

}
