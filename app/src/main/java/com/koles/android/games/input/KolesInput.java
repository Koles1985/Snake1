package com.koles.android.games.input;

import android.content.Context;
import android.os.Build;
import android.view.View;

import java.util.List;

public class KolesInput implements Input{

    TouchHandler touchHandler;

    public KolesInput(Context context, View view, float scaleX, float scaleY){
        if(Build.VERSION.SDK_INT < 5){
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        }else{
            touchHandler = new MultitouchHandler(view, scaleX, scaleY);
        }
    }

    @Override
    public boolean isTouchDown(int pointer) {
       return touchHandler.isTouched(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return touchHandler.touchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return touchHandler.touchY(pointer);
    }

    @Override
    public List<TouchEvent> touchEvents() {
        return touchHandler.getTouchEvents();
    }
}
