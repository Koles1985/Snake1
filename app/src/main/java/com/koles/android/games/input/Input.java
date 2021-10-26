package com.koles.android.games.input;

import java.util.List;

public interface Input {
    public static class TouchEvent{
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;

        private int type;
        private int x;
        private int y;
        private int pointer;

        public void setType(int type){
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public void setX(int x){
            this.x = x;
        }

        public int getX(){
            return x;
        }

        public void setY(int y){
            this.y = y;
        }

        public int getY(){
            return y;
        }

        public void setPointer(int pointer){
            this.pointer = pointer;
        }

        public int getPointer(){
            return pointer;
        }
    }

    boolean isTouchDown(int pointer);
    int getTouchX(int pointer);
    int getTouchY(int pointer);
    List<TouchEvent> touchEvents();
}
