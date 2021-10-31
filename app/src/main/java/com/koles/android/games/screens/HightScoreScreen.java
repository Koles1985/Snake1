package com.koles.android.games.screens;

import com.koles.android.games.graphics.Assets;
import com.koles.android.games.graphics.Graphics;
import com.koles.android.games.input.Input;
import com.koles.android.games.mechanicsgame.Game;
import com.koles.android.games.mechanicsgame.Screen;
import com.koles.android.games.mechanicsgame.Settings;

import java.util.List;

public class HightScoreScreen extends Screen {
    String[] lines = new String[5];
    public HightScoreScreen(Game game){
        super(game);
        for( int i = 0; i < lines.length; i++){
            lines[i] = "" + (i + 1) + ". " + Settings.highScores[i];
        }
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().touchEvents();
        for(int i = 0; i < touchEvents.size(); i++){
            Input.TouchEvent event = touchEvents.get(i);
            if(event.getType() == Input.TouchEvent.TOUCH_UP){
                if(event.getX() < 64 && event.getY() > 416){
                    if(Settings.soundEnabled){
                        Assets.click.play(1);
                    }
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0,0);
        g.drawPixmap(Assets.mainMenu, 64, 20, 0, 42, 196, 42);

        int y = 100;
        for(int i = 0; i < 5; i++){
            drawText(g, lines[i], 20, y);
            y += 50;
        }

        g.drawPixmap(Assets.buttons, 0, 416,64,64,64,64);
    }

    public  void drawText(Graphics g, String line, int x, int y){
        for(int i = 0; i < line.length(); i ++){
            char ch = line.charAt(i);

            if(ch == ' '){
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if(ch == '.'){
                srcX = 200;
                srcWidth = 10;
            }else{
                srcX = (ch - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
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
