package com.koles.android.games.audio;


import java.io.IOException;

public interface Audio {
    LongMusic newLongMusic(String longMusicFile) throws IOException;
    SoundEffect newSoundEffect(String soundEffectFile) throws IOException;
}
