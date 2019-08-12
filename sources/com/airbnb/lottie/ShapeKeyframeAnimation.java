package com.airbnb.lottie;

import android.graphics.Path;
import java.util.List;

class ShapeKeyframeAnimation extends BaseKeyframeAnimation<ShapeData, Path> {
    private final Path tempPath = new Path();
    private final ShapeData tempShapeData = new ShapeData();

    ShapeKeyframeAnimation(List<Keyframe<ShapeData>> keyframes) {
        super(keyframes);
    }

    public Path getValue(Keyframe<ShapeData> keyframe, float keyframeProgress) {
        this.tempShapeData.interpolateBetween((ShapeData) keyframe.startValue, (ShapeData) keyframe.endValue, keyframeProgress);
        MiscUtils.getPathFromData(this.tempShapeData, this.tempPath);
        return this.tempPath;
    }
}
