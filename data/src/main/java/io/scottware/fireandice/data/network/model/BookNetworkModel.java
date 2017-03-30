package io.scottware.fireandice.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookNetworkModel {

    @SerializedName("url")
    public final String url;

    @SerializedName("name")
    public final String name;

    @SerializedName("characters")
    public final List<String> characterUrls;

    public BookNetworkModel(String url, String name, List<String> characterUrls) {
        this.url = url;
        this.name = name;
        this.characterUrls = characterUrls;
    }

}
