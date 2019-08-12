package com.airbnb.android.core.views.svg;

import android.graphics.Path;
import android.graphics.PathMeasure;

public class SvgPath {
    final float length = this.measure.getLength();
    final PathMeasure measure;
    final Path path;

    SvgPath(Path path2) {
        this.path = new Path(path2);
        this.measure = new PathMeasure(path2, false);
    }
}
