package com.airbnb.lottie;

import android.graphics.PointF;
import java.util.List;

class PointKeyframeAnimation extends KeyframeAnimation<PointF> {
    private final PointF point = new PointF();

    PointKeyframeAnimation(List<Keyframe<PointF>> keyframes) {
        super(keyframes);
    }

    public PointF getValue(Keyframe<PointF> keyframe, float keyframeProgress) {
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF startPoint = (PointF) keyframe.startValue;
        PointF endPoint = (PointF) keyframe.endValue;
        this.point.set(startPoint.x + ((endPoint.x - startPoint.x) * keyframeProgress), startPoint.y + ((endPoint.y - startPoint.y) * keyframeProgress));
        return this.point;
    }
}
