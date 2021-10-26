package com.koles.android.games.audio;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

public class KolesAudio implements Audio{
    AssetManager assetMnager;
    SoundPool soundPool;
    AudioAttributes attributes;

    public KolesAudio(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assetMnager = activity.getAssets();
        attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder().setMaxStreams(20)
                .setAudioAttributes(attributes)
                .build();
    }

    @Override
    public LongMusic newLongMusic(String longMusicFile){
       KolesLongMusic kolesLongMusic = null;
        try{
           AssetFileDescriptor descriptor = assetMnager.openFd(longMusicFile);
           kolesLongMusic = new KolesLongMusic(descriptor);
       }catch(IOException e){
           System.out.println(e.getMessage() + " - KolesAudio.newLongMusic exception");
       }
            return kolesLongMusic;
    }

    @Override
    public SoundEffect newSoundEffect(String soundEffectFile){
        KolesSoundEffect kolesSoundEffect = null;
        try{
            AssetFileDescriptor descriptor = assetMnager.openFd(soundEffectFile);
            int soundId = soundPool.load(descriptor, 0);
            kolesSoundEffect = new KolesSoundEffect(soundPool, soundId);
        }catch(IOException e){
            System.out.println(e.getMessage() + " - KolesAudio.newSoundEffect exception");
        }
        return kolesSoundEffect;
    }
}
