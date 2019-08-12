package com.airbnb.lottie;

import android.graphics.PointF;
import com.airbnb.lottie.AnimatableValue.Factory;
import org.json.JSONArray;
import org.json.JSONObject;

class PointFFactory implements Factory<PointF> {
    static final PointFFactory INSTANCE = new PointFFactory();

    private PointFFactory() {
    }

    public PointF valueFromObject(Object object, float scale) {
        if (object instanceof JSONArray) {
            return JsonUtils.pointFromJsonArray((JSONArray) object, scale);
        }
        if (object instanceof JSONObject) {
            return JsonUtils.pointFromJsonObject((JSONObject) object, scale);
        }
        throw new IllegalArgumentException("Unable to parse point from " + object);
    }
}
