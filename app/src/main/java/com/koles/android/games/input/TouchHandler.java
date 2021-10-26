package com.koles.android.games.input;

import android.view.View.OnTouchListener;

import java.util.List;

import com.koles.android.games.input.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    boolean isTouched(int pointer);
    int touchX(int pointer);
    int touchY(int pointer);
    List<TouchEvent> getTouchEvents();

}
