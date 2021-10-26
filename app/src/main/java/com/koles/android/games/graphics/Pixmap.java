package com.koles.android.games.graphics;

import com.koles.android.games.graphics.Graphics.PixmapFormat;
public interface Pixmap {
    int getWidth();
    int getHeight();
    PixmapFormat getFormat();
    void dispose();
}
