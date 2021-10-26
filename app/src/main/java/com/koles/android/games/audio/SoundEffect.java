package com.koles.android.games.audio;

public interface SoundEffect {
    /**
     * @void play
     * принимает на вход уровень громкости
     * воспроизведения звука
     * 0 - без звука
     * 1 - оглохнуть(максимум)
     */
    void play(float volume);
    void dispose();
}
