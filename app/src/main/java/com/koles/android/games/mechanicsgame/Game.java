package com.koles.android.games.mechanicsgame;

import com.koles.android.games.audio.Audio;
import com.koles.android.games.graphics.Graphics;
import com.koles.android.games.input.Input;
import com.koles.android.games.io.FileIO;

public interface Game {
    Input getInput();
    FileIO getFileIO();
    Audio getAudio();
    Graphics getGraphics();
    void setScreen(Screen screen);
    Screen getCurrentScreen();
    Screen getStartScreen();
}
