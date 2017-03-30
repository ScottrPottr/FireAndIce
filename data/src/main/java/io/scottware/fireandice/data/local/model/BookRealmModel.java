package io.scottware.fireandice.data.local.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BookRealmModel extends RealmObject {

    @PrimaryKey
    private String url;

    private String name;

    private RealmList<CharacterRealmModel> characters;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<CharacterRealmModel> getCharacters() {
        return characters;
    }

    public void setCharacters(RealmList<CharacterRealmModel> characters) {
        this.characters = characters;
    }

}
