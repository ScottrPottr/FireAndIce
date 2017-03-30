package io.scottware.fireandice.data.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.RealmList;
import io.scottware.fireandice.data.local.model.BookRealmModel;
import io.scottware.fireandice.data.local.model.CharacterRealmModel;
import io.scottware.fireandice.data.network.model.BookNetworkModel;

@Singleton
public class BookNetworkToRealmMapper {

    @Inject
    BookNetworkToRealmMapper() {}

    public BookRealmModel transform(BookNetworkModel bookNetworkModel) {
        BookRealmModel bookRealmModel = new BookRealmModel();
        bookRealmModel.setUrl(bookNetworkModel.url);
        bookRealmModel.setName(bookNetworkModel.name);

        if (bookNetworkModel.characterUrls != null) {
            RealmList<CharacterRealmModel> characterEntities = new RealmList<>();
            for (String characterUrl : bookNetworkModel.characterUrls) {
                CharacterRealmModel characterRealmModel = new CharacterRealmModel();
                characterRealmModel.setUrl(characterUrl);
                characterEntities.add(characterRealmModel);
            }
            bookRealmModel.setCharacters(characterEntities);
        }
        return bookRealmModel;
    }

    public List<BookRealmModel> transform(List<BookNetworkModel> booksResponse) {
        List<BookRealmModel> bookEntities = new ArrayList<>();
        for (BookNetworkModel bookNetworkModel : booksResponse) {
            bookEntities.add(transform(bookNetworkModel));
        }
        return bookEntities;
    }

}
