package com.koles.android.games.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.koles.android.games.mechanicsgame.KolesGame;

public class KolesFastRenderView extends SurfaceView implements Runnable{

    KolesGame game;
    Bitmap frameBuffer;
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean running = false;

    public KolesFastRenderView(KolesGame game, Bitmap frameBuffer){
        super(game);
        this.game = game;
        this.frameBuffer = frameBuffer;
        this.holder = getHolder();
    }

    public void resume(){
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

    @Override
    public void run() {
        Rect dstRect = new Rect();
        long startTime = System.nanoTime();
        while(running){
            if(!holder.getSurface().isValid()){
                continue;
            }
            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            game.getCurrentScreen().update(deltaTime);
            game.getCurrentScreen().present(deltaTime);

            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(frameBuffer, null, dstRect, null);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause(){
        running = false;
        boolean flag = true;
        while(flag){
            try{
                renderThread.join();
                flag = renderThread.isAlive();
            }catch(InterruptedException e){
                System.out.println(e.getMessage() + " - KolesFastRenderView.puse - exception");
                continue;
            }
        }
    }
}
