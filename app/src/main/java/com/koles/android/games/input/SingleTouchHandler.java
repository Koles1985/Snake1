package com.koles.android.games.input;
import android.view.MotionEvent;
import android.view.View;

import com.koles.android.games.input.Input.TouchEvent;
import com.koles.android.games.input.Pool.PoolObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class SingleTouchHandler implements TouchHandler{
    boolean isTouched;
    int touchX;
    int touchY;
    Pool<TouchEvent> touchEventPool;
    List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
    List<TouchEvent> touchEventBuffer = new ArrayList<TouchEvent>();
    float scaleX;
    float scaleY;

    public SingleTouchHandler(View view, float scaleX, float scaleY){
        PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {
            @Override
            public TouchEvent creteObject() {
                return new TouchEvent();
            }
        };

        touchEventPool = new Pool(factory, 100);
        view.setOnTouchListener(this);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this) {
            TouchEvent touchEvent = touchEventPool.newObject();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    touchEvent.setType(TouchEvent.TOUCH_DOWN);
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.setType(TouchEvent.TOUCH_DRAGGED);
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    touchEvent.setType(TouchEvent.TOUCH_UP);
                    isTouched = false;
                    break;
            }

            touchEvent.setX((int)(event.getX() * scaleX));
            touchX = touchEvent.getX();
            touchEvent.setY((int)(event.getY() * scaleY));
            touchY = touchEvent.getY();

            touchEventBuffer.add(touchEvent);
            return true;
        }

    }

    @Override
    public boolean isTouched(int pointer) {
        synchronized (this){
            if(pointer == 0){
                return isTouched;
            }else{
                return false;
            }
        }

    }

    @Override
    public int touchX(int pointer) {
        synchronized (this){
            return touchX;
        }
    }

    @Override
    public int touchY(int pointer) {
        synchronized (this){
            return touchY;
        }
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        synchronized (this){
            for(int i = 0; i < touchEvents.size(); i++){
                touchEventPool.tryAddObject(touchEvents.get(i));
            }
            touchEvents.clear();
            touchEvents.addAll(touchEventBuffer);
            touchEventBuffer.clear();
            return touchEvents;
        }
    }
}
