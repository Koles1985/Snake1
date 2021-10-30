package com.koles.android.games.graphics;

import android.graphics.Bitmap;
import com.koles.android.games.graphics.Graphics.PixmapFormat;

public class KolesPixmap implements Pixmap{

    Bitmap bitmap;
    PixmapFormat pixmapFormat;

    public KolesPixmap(Bitmap bitmap, PixmapFormat format){
        this.bitmap = bitmap;
        this.pixmapFormat = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public PixmapFormat getFormat() {
        return pixmapFormat;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
