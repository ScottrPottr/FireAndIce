package io.scottware.fireandice.presentation.navigation;

import android.app.Activity;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.scottware.fireandice.presentation.presenter.CharactersListPresenter;
import io.scottware.fireandice.presentation.view.activity.CharacterListActivity;
import io.scottware.fireandice.presentation.view.activity.SplashActivity;

@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToSplashScreen(Activity startingActivity) {
        if (!(startingActivity instanceof SplashActivity)) {
            Intent intentToLaunch = new Intent(startingActivity, SplashActivity.class);
            startingActivity.startActivity(intentToLaunch);
        }
    }

    public void navigateToCharactersList(Activity startingActivity, String bookUrl) {
        Intent intentToLaunch = new Intent(startingActivity, CharacterListActivity.class);
        intentToLaunch.putExtra(CharactersListPresenter.INITIAL_DATA_BOOK, bookUrl);
        startingActivity.startActivity(intentToLaunch);
    }

}
