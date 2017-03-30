package io.scottware.fireandice.data.repository;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.scottware.fireandice.data.local.CharacterRealm;
import io.scottware.fireandice.data.local.model.CharacterRealmModel;
import io.scottware.fireandice.data.mapper.CharacterNetworkToRealmMapper;
import io.scottware.fireandice.data.network.ICharacterService;
import io.scottware.fireandice.domain.executor.ThreadExecutor;
import io.scottware.fireandice.domain.repositories.ICharactersRepository;

@Singleton
public class CharactersRepository implements ICharactersRepository {

    private ICharacterService characterService;
    private CharacterRealm characterRealm;
    private CharacterNetworkToRealmMapper characterNetworkToRealmMapper;
    private ThreadExecutor threadExecutor;

    @Inject
    public CharactersRepository(ICharacterService characterService, CharacterRealm characterRealm, CharacterNetworkToRealmMapper characterNetworkToRealmMapper, ThreadExecutor threadExecutor) {
        this.characterService = characterService;
        this.characterRealm = characterRealm;
        this.characterNetworkToRealmMapper = characterNetworkToRealmMapper;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public Observable<Long> loadCharacters(List<String> characterUrls) {
        return Observable.fromCallable(() -> {
            Observable.fromIterable(characterUrls)
                    .observeOn(Schedulers.from(threadExecutor))
                    .subscribeOn(Schedulers.from(threadExecutor))
                    .forEach(characterUrl -> {
                        Log.d("CharRepo", "Loading char url: " + characterUrl);
                        characterService.getCharacter(characterUrl).doOnNext(characterResponse -> {
                            Log.d("CharRepo", "Storing char: " + characterResponse.name);
                            characterRealm.storeCharacter(characterNetworkToRealmMapper.transform(characterResponse));
                            Log.d("CharRepo", "Successfully stored char: " + characterResponse.name);
                        }).subscribeOn(Schedulers.from(threadExecutor)).observeOn(Schedulers.from(threadExecutor)).subscribe();
                    });
            return 0L;
        });
    }

    @Override
    public Observable<Long> loadCharacters(String bookUrl, int start, int offset) {
        Log.d("CharRepo", "Loading chars book url: " + bookUrl + " start: " + start + ", offset: " + offset);
        List<CharacterRealmModel> localEntities = characterRealm.getCharacters(bookUrl);
        List<CharacterRealmModel> nextPage = localEntities.subList(start, Math.min(start + offset, localEntities.size() - 1));
        Log.d("CharRepo", "Loading chars local entities for url " + localEntities.size());
        List<String> charactersToLoad = new ArrayList<>();
        for (CharacterRealmModel characterRealmModel : nextPage) {
            if (characterRealmModel.getName() == null) {
                charactersToLoad.add(characterRealmModel.getUrl());
            }
        }
        Log.d("CharRepo", "Loading chars needing " + charactersToLoad.size());
        return loadCharacters(charactersToLoad);
    }


}
