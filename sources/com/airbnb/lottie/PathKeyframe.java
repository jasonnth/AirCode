package com.airbnb.lottie;

import android.graphics.Path;
import android.graphics.PointF;
import android.view.animation.Interpolator;
import org.json.JSONArray;
import org.json.JSONObject;

class PathKeyframe extends Keyframe<PointF> {
    /* access modifiers changed from: private */
    public Path path;

    static class Factory {
        static PathKeyframe newInstance(JSONObject json, LottieComposition composition, com.airbnb.lottie.AnimatableValue.Factory<PointF> valueFactory) {
            Keyframe<PointF> keyframe = Factory.newInstance(json, composition, composition.getDpScale(), valueFactory);
            PointF cp1 = null;
            PointF cp2 = null;
            JSONArray tiJson = json.optJSONArray("ti");
            JSONArray toJson = json.optJSONArray("to");
            if (!(tiJson == null || toJson == null)) {
                cp1 = JsonUtils.pointFromJsonArray(toJson, composition.getDpScale());
                cp2 = JsonUtils.pointFromJsonArray(tiJson, composition.getDpScale());
            }
            PathKeyframe pathKeyframe = new PathKeyframe(composition, (PointF) keyframe.startValue, (PointF) keyframe.endValue, keyframe.interpolator, keyframe.startFrame, keyframe.endFrame);
            boolean equals = (keyframe.endValue == null || keyframe.startValue == null || !((PointF) keyframe.startValue).equals(((PointF) keyframe.endValue).x, ((PointF) keyframe.endValue).y)) ? false : true;
            if (pathKeyframe.endValue != null && !equals) {
                pathKeyframe.path = Utils.createPath((PointF) keyframe.startValue, (PointF) keyframe.endValue, cp1, cp2);
            }
            return pathKeyframe;
        }
    }

    private PathKeyframe(LottieComposition composition, PointF startValue, PointF endValue, Interpolator interpolator, float startFrame, Float endFrame) {
        super(composition, startValue, endValue, interpolator, startFrame, endFrame);
    }

    /* access modifiers changed from: 0000 */
    public Path getPath() {
        return this.path;
    }
}
