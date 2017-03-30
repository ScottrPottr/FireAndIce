package io.scottware.fireandice.data.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.scottware.fireandice.data.local.model.CharacterRealmModel;
import io.scottware.fireandice.data.network.model.CharacterNetworkModel;

@Singleton
public class CharacterNetworkToRealmMapper {

    @Inject
    CharacterNetworkToRealmMapper() {}

    public CharacterRealmModel transform(CharacterNetworkModel characterNetworkModel) {
        CharacterRealmModel characterRealmModel = new CharacterRealmModel();
        characterRealmModel.setUrl(characterNetworkModel.url);
        characterRealmModel.setName(characterNetworkModel.name);
        return characterRealmModel;
    }

    public List<CharacterRealmModel> transform(List<CharacterNetworkModel> characterNetworkModels) {
        List<CharacterRealmModel> characterEntities = new ArrayList<>();
        for (CharacterNetworkModel characterNetworkModel : characterNetworkModels) {
            characterEntities.add(transform(characterNetworkModel));
        }
        return characterEntities;
    }

}
