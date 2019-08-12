package com.roughike.bottombar;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

class BadgeCircle {
    static ShapeDrawable make(int color) {
        ShapeDrawable indicator = new ShapeDrawable(new OvalShape());
        indicator.getPaint().setColor(color);
        return indicator;
    }
}
