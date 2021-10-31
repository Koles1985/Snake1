package com.koles.android.games.screens;

import com.koles.android.games.graphics.Assets;
import com.koles.android.games.graphics.Graphics;
import com.koles.android.games.input.Input;
import com.koles.android.games.mechanicsgame.Game;
import com.koles.android.games.mechanicsgame.Screen;
import com.koles.android.games.mechanicsgame.Settings;

import java.util.List;

public class HelpScreen extends Screen {
    public HelpScreen(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvent = game.getInput().touchEvents();

        for(int i = 0; i < touchEvent.size(); i++){
            Input.TouchEvent event = touchEvent.get(i);
            if(event.getType() == Input.TouchEvent.TOUCH_UP){
                if(event.getX() > 256 && event.getY() > 416 ){
                    //game.setScreen(new HelpScreen2(game));
                    if(Settings.soundEnabled){
                        Assets.click.play(1);
                    }
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background,0,0);
        g.drawPixmap(Assets.help1, 64, 100);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 64,64,64);
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
