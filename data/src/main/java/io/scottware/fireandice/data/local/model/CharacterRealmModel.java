package io.scottware.fireandice.data.local.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CharacterRealmModel extends RealmObject {

    @PrimaryKey
    private String url;

    private String name;

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

}


