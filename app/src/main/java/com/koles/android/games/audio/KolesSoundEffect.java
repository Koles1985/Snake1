package com.koles.android.games.audio;

import android.media.SoundPool;

public class KolesSoundEffect implements SoundEffect{
    int soundId;
    SoundPool soundPool;

    public KolesSoundEffect(SoundPool soundPool, int soundId){
        this.soundPool = soundPool;
        this.soundId = soundId;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0,0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }
}
