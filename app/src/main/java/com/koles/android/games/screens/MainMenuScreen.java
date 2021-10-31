package com.koles.android.games.screens;

import com.koles.android.games.graphics.Assets;
import com.koles.android.games.graphics.Graphics;
import com.koles.android.games.input.Input;
import com.koles.android.games.mechanicsgame.Game;
import com.koles.android.games.mechanicsgame.Screen;
import com.koles.android.games.mechanicsgame.Settings;

import java.util.List;

public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().touchEvents();
        for(int i = 0; i < touchEvents.size(); i++){
            Input.TouchEvent event = touchEvents.get(i);
            if(event.getType() == Input.TouchEvent.TOUCH_UP){
                if(inBounds(event, 0, g.getHeight() - 64, 64, 64 )){
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled){
                        Assets.click.play(1);
                    }
                }
                if(inBounds(event, 64, 220, 192, 42)){
                    //game.setScreen(new GameScreen(game));
                    if(Settings.soundEnabled){
                        Assets.click.play(1);
                    }
                    return;
                }
                if(inBounds(event, 64, 220 + 42, 192, 42)){
                    //game.setScreen(new HightScoreScreen(game));
                    if(Settings.soundEnabled){
                        Assets.click.play(1);
                    }
                    return;
                }

                if(inBounds(event, 64, 220 + 84, 192, 42)){
                   // game.setScreen(new HelpScreen(game));
                    if(Settings.soundEnabled){
                        Assets.click.play(1);
                    }
                    return;
                }
            }
        }
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height){
        if(event.getX() > x && event.getX() < x + width - 1
        && event.getY() > y && event.getY() < y + height - 1){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
         g.drawPixmap(Assets.background,0,0);
         g.drawPixmap(Assets.logo, 32, 20);
         g.drawPixmap(Assets.mainMenu, 64, 220);
         if(Settings.soundEnabled){
             g.drawPixmap(Assets.buttons, 0, 416, 0,0, 64,64);
         }else{
             g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
         }
    }

    @Override
    public void pause() {
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
