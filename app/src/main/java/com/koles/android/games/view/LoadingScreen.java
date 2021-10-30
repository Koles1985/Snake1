package com.koles.android.games.view;

import com.koles.android.games.audio.SoundEffect;
import com.koles.android.games.graphics.Assets;
import com.koles.android.games.graphics.Graphics;
import com.koles.android.games.mechanicsgame.Game;
import com.koles.android.games.mechanicsgame.Screen;
import com.koles.android.games.mechanicsgame.Settings;

public class LoadingScreen extends Screen {

    public LoadingScreen(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background_game.png",
                Graphics.PixmapFormat.RGB565);

        Assets.logo = g.newPixmap("logo.png", Graphics.PixmapFormat.ARGB4444);
        Assets.mainMenu = g.newPixmap("mainmenu.png", Graphics.PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("button_map.png", Graphics.PixmapFormat.ARGB4444);
        Assets.help1 = g.newPixmap("help1.png", Graphics.PixmapFormat.ARGB4444);
        Assets.help2 = g.newPixmap("help2.png", Graphics.PixmapFormat.ARGB4444);
        Assets.help3 = g.newPixmap("help3.png", Graphics.PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", Graphics.PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("ready.png", Graphics.PixmapFormat.ARGB4444);
        Assets.pause = g.newPixmap("paus.png", Graphics.PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("game_ower.png", Graphics.PixmapFormat.ARGB4444);
        Assets.headUp = g.newPixmap("head_up.png", Graphics.PixmapFormat.ARGB4444);
        Assets.headLeft = g.newPixmap("head_left.png", Graphics.PixmapFormat.ARGB4444);
        Assets.headDown = g.newPixmap("head_down.png", Graphics.PixmapFormat.ARGB4444);
        Assets.headRight = g.newPixmap("head_right.png", Graphics.PixmapFormat.ARGB4444);
        Assets.tail = g.newPixmap("tail.png", Graphics.PixmapFormat.ARGB4444);
        Assets.stain1 = g.newPixmap("stain1.png", Graphics.PixmapFormat.ARGB4444);
        Assets.stain2 = g.newPixmap("stain2.png", Graphics.PixmapFormat.ARGB4444);
        Assets.stain3 = g.newPixmap("stain3.png", Graphics.PixmapFormat.ARGB4444);

        Assets.click = game.getAudio().newSoundEffect("click.ogg");
        Assets.eat = game.getAudio().newSoundEffect("eat.ogg");
        Assets.bitten = game.getAudio().newSoundEffect("canibal.ogg");

        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
