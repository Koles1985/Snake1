package com.koles.android.games.audio;

public interface LongMusic {
    void play();
    void stop();
    void pause();

    /**
     * @setLooping
     * зациклить воспроизведение?
     * true - зациклить
     * false - однократное воспроизведение
     */
    void setLooping(boolean looping);

    /**
     * @setVolume
     * Громкость воспроизведения
     * 0 - без звука
     * 1 - кровь из ушей
     */
    void setVolume(float volume);

    boolean isPlaying();
    boolean isStoped();
    boolean isLooping();

    void dispose();

}
