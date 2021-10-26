package com.koles.android.games.mechanicsgame;

public abstract class Screen {
    protected final Game game;

    public Screen(Game game){
        this.game = game;
    }

    public abstract void update(float deltaTime);
    public abstract void presenr(float deltaTime);
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
}