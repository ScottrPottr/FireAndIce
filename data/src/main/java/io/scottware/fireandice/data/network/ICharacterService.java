package io.scottware.fireandice.data.network;

import io.reactivex.Observable;
import io.scottware.fireandice.data.network.model.CharacterNetworkModel;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ICharacterService {

    @GET
    Observable<CharacterNetworkModel> getCharacter(@Url String url);

}
