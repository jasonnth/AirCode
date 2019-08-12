package com.airbnb.android.lib.utils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Iterator;

public class DrawableCompositor extends Drawable {
    private final ArrayList<Drawable> mDrawables = new ArrayList<>(3);

    public void addDrawable(Drawable slash) {
        this.mDrawables.add(slash);
    }

    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        Iterator it = this.mDrawables.iterator();
        while (it.hasNext()) {
            ((Drawable) it.next()).setBounds(left, top, right, bottom);
        }
    }

    public void setBounds(Rect bounds) {
        super.setBounds(bounds);
        Iterator it = this.mDrawables.iterator();
        while (it.hasNext()) {
            ((Drawable) it.next()).setBounds(bounds);
        }
    }

    public void draw(Canvas canvas) {
        Iterator it = this.mDrawables.iterator();
        while (it.hasNext()) {
            ((Drawable) it.next()).draw(canvas);
        }
    }

    public int getOpacity() {
        return -1;
    }

    public void setAlpha(int alpha) {
        Iterator it = this.mDrawables.iterator();
        while (it.hasNext()) {
            ((Drawable) it.next()).setAlpha(alpha);
        }
    }

    public void setColorFilter(ColorFilter cf) {
        Iterator it = this.mDrawables.iterator();
        while (it.hasNext()) {
            ((Drawable) it.next()).setColorFilter(cf);
        }
    }
}
