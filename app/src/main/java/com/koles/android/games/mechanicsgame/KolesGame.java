package com.koles.android.games.mechanicsgame;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.koles.android.games.audio.Audio;
import com.koles.android.games.audio.KolesAudio;
import com.koles.android.games.graphics.Graphics;
import com.koles.android.games.graphics.KolesFastRenderView;
import com.koles.android.games.graphics.KolesGraphics;
import com.koles.android.games.input.Input;
import com.koles.android.games.input.KolesInput;
import com.koles.android.games.io.FileIO;
import com.koles.android.games.io.KolesFileIO;

public class KolesGame extends Activity implements Game{

    KolesFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        boolean isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        int frameBufferWidth = isLandscape ? 480 : 320;
        int frameBufferHeight = isLandscape ? 320 : 480;

        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight,
                Bitmap.Config.RGB_565);

        float scaleX = (float)(frameBufferWidth / getWindowManager().getDefaultDisplay()
        .getWidth());

        float scaleY = (float)(frameBufferHeight / getWindowManager().getDefaultDisplay()
        .getHeight());

        renderView = new KolesFastRenderView(this, frameBuffer);
        graphics = new KolesGraphics(getAssets(), frameBuffer);
        fileIO = new KolesFileIO(getAssets());
        audio = new KolesAudio(this);
        input = new KolesInput(this, renderView, scaleX, scaleY);
        screen = getStartScreen();//еще не рализован метод
        setContentView(renderView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        screen.resume();
        renderView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        renderView.pause();
        screen.pause();
        if(isFinishing()){
            screen.dispose();
        }
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public void setScreen(Screen screen) {
        if(screen == null){
            throw new IllegalStateException("Screen must not be null");
        }
        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }

    @Override
    public Screen getStartScreen() {
        return null;
    }
}
