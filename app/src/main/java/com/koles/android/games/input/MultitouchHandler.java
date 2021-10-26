package com.koles.android.games.input;

import android.view.MotionEvent;
import android.view.View;

import com.koles.android.games.input.Input.TouchEvent;
import com.koles.android.games.input.Pool.PoolObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class MultitouchHandler implements TouchHandler{
    boolean[] isTouched = new boolean[20];
    int[] touchX = new int[20];
    int[] touchY = new int[20];

    float scaleX;
    float scaleY;

    Pool<TouchEvent> touchEventPool;
    List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
    List<TouchEvent> touchEventBuffer = new ArrayList<TouchEvent>();


    public MultitouchHandler(View view, float scaleX, float scaleY){
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
        synchronized (this){
            int action = event.getActionMasked();
            int pointerIndex = event.getActionIndex();
            int pointerId = event.getPointerId(pointerIndex);

            TouchEvent touchEvent;
            switch(action){
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    touchEvent = touchEventPool.newObject();
                    touchEvent.setType(TouchEvent.TOUCH_DOWN);
                    touchEvent.setPointer(pointerId);
                    touchEvent.setX((int)(event.getX(pointerIndex) * scaleX));
                    touchX[pointerId] = touchEvent.getX();
                    touchEvent.setY((int)(event.getY() * scaleY));
                    touchY[pointerId] = touchEvent.getY();
                    isTouched[pointerId] = true;
                    touchEventBuffer.add(touchEvent);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_POINTER_UP:
                    touchEvent = touchEventPool.newObject();
                    touchEvent.setType(TouchEvent.TOUCH_UP);
                    touchEvent.setType(pointerId);
                    touchEvent.setX((int)(event.getX() * scaleX));
                    touchEvent.setY((int)(event.getY() * scaleY));
                    touchX[pointerId] = touchEvent.getX();
                    touchY[pointerId] = touchEvent.getY();
                    isTouched[pointerId] = false;
                    touchEventBuffer.add(touchEvent);
                    break;

                case MotionEvent.ACTION_MOVE:
                    int pointerCount = event.getPointerCount();
                    for(int i = 0; i < pointerCount; i++){
                        pointerIndex = i;
                        pointerId = event.getPointerId(pointerIndex);
                        touchEvent = touchEventPool.newObject();
                        touchEvent.setType(TouchEvent.TOUCH_DRAGGED);
                        touchEvent.setX((int)(event.getX() * scaleX));
                        touchEvent.setY((int)(event.getY() * scaleY));
                        touchX[pointerId] = touchEvent.getX();
                        touchY[pointerId] = touchEvent.getY();
                        touchEventBuffer.add(touchEvent);
                    }
                    break;
            }
            return true;
        }
    }

    @Override
    public boolean isTouched(int pointer) {
        synchronized (this){
            if(pointer < isTouched.length & pointer >= 0){
                return isTouched[pointer];
            }else{
                return false;
            }
        }
    }

    @Override
    public int touchX(int pointer) {
        synchronized (this){
            if(pointer < touchX.length & pointer >= 0){
                return touchX[pointer];
            }else{
                return 0;
            }
        }
    }

    @Override
    public int touchY(int pointer) {
        synchronized(this){
            if(pointer < touchY.length & pointer >= 0){
                return touchY[pointer];
            }else{
                return 0;
            }
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
