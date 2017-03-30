package io.scottware.fireandice.data.network.model;

import com.google.gson.annotations.SerializedName;

public class CharacterNetworkModel {

    @SerializedName("url")
    public final String url;

    @SerializedName("name")
    public final String name;

    public CharacterNetworkModel(String url, String name) {
        this.url = url;
        this.name = name;
    }

}
