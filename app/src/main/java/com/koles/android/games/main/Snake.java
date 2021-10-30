package com.koles.android.games.main;

import com.koles.android.games.mechanicsgame.KolesGame;
import com.koles.android.games.view.LoadingScreen;
import com.koles.android.games.mechanicsgame.Screen;


public class Snake extends KolesGame {

    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);

    }
}
