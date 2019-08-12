package com.airbnb.lottie;

import android.graphics.Color;
import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

class Layer {
    private static final String TAG = Layer.class.getSimpleName();
    private final LottieComposition composition;
    private final List<Keyframe<Float>> inOutKeyframes;
    private final long layerId;
    private final String layerName;
    private final LayerType layerType;
    private final List<Mask> masks;
    private final MatteType matteType;
    private final long parentId;
    private final int preCompHeight;
    private final int preCompWidth;
    private final String refId;
    private final List<Object> shapes;
    private final int solidColor;
    private final int solidHeight;
    private final int solidWidth;
    private final float startProgress;
    private final float timeStretch;
    private final AnimatableTransform transform;

    static class Factory {
        static Layer newInstance(LottieComposition composition) {
            Rect bounds = composition.getBounds();
            return new Layer(Collections.emptyList(), composition, null, -1, LayerType.PreComp, -1, null, Collections.emptyList(), Factory.newInstance(), 0, 0, 0, 0.0f, 0.0f, bounds.width(), bounds.height(), Collections.emptyList(), MatteType.None);
        }

        static Layer newInstance(JSONObject json, LottieComposition composition) {
            LayerType layerType;
            String layerName = json.optString("nm");
            String refId = json.optString("refId");
            long layerId = json.optLong("ind");
            int solidWidth = 0;
            int solidHeight = 0;
            int solidColor = 0;
            int preCompWidth = 0;
            int preCompHeight = 0;
            int layerTypeInt = json.optInt("ty", -1);
            if (layerTypeInt < LayerType.Unknown.ordinal()) {
                layerType = LayerType.values()[layerTypeInt];
            } else {
                layerType = LayerType.Unknown;
            }
            long parentId = json.optLong("parent", -1);
            if (layerType == LayerType.Solid) {
                solidWidth = (int) (((float) json.optInt("sw")) * composition.getDpScale());
                solidHeight = (int) (((float) json.optInt("sh")) * composition.getDpScale());
                solidColor = Color.parseColor(json.optString("sc"));
            }
            AnimatableTransform transform = Factory.newInstance(json.optJSONObject("ks"), composition);
            MatteType matteType = MatteType.values()[json.optInt("tt")];
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            JSONArray jsonMasks = json.optJSONArray("masksProperties");
            if (jsonMasks != null) {
                for (int i = 0; i < jsonMasks.length(); i++) {
                    arrayList2.add(Factory.newMask(jsonMasks.optJSONObject(i), composition));
                }
            }
            JSONArray shapesJson = json.optJSONArray("shapes");
            if (shapesJson != null) {
                for (int i2 = 0; i2 < shapesJson.length(); i2++) {
                    Object shape = ShapeGroup.shapeItemWithJson(shapesJson.optJSONObject(i2), composition);
                    if (shape != null) {
                        arrayList.add(shape);
                    }
                }
            }
            float timeStretch = (float) json.optDouble("sr", 1.0d);
            float startProgress = ((float) json.optDouble("st")) / composition.getDurationFrames();
            if (layerType == LayerType.PreComp) {
                preCompWidth = (int) (((float) json.optInt("w")) * composition.getDpScale());
                preCompHeight = (int) (((float) json.optInt("h")) * composition.getDpScale());
            }
            float inFrame = (float) json.optLong("ip");
            float outFrame = (float) json.optLong("op");
            if (inFrame > 0.0f) {
                ArrayList arrayList4 = arrayList3;
                arrayList4.add(new Keyframe<>(composition, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(inFrame)));
            }
            if (outFrame <= 0.0f) {
                outFrame = (float) (composition.getEndFrame() + 1);
            }
            ArrayList arrayList5 = arrayList3;
            arrayList5.add(new Keyframe<>(composition, Float.valueOf(1.0f), Float.valueOf(1.0f), null, inFrame, Float.valueOf(outFrame)));
            if (outFrame <= composition.getDurationFrames()) {
                ArrayList arrayList6 = arrayList3;
                arrayList6.add(new Keyframe<>(composition, Float.valueOf(0.0f), Float.valueOf(0.0f), null, outFrame, Float.valueOf((float) composition.getEndFrame())));
            }
            return new Layer(arrayList, composition, layerName, layerId, layerType, parentId, refId, arrayList2, transform, solidWidth, solidHeight, solidColor, timeStretch, startProgress, preCompWidth, preCompHeight, arrayList3, matteType);
        }
    }

    enum LayerType {
        PreComp,
        Solid,
        Image,
        Null,
        Shape,
        Text,
        Unknown
    }

    enum MatteType {
        None,
        Add,
        Invert,
        Unknown
    }

    private Layer(List<Object> shapes2, LottieComposition composition2, String layerName2, long layerId2, LayerType layerType2, long parentId2, String refId2, List<Mask> masks2, AnimatableTransform transform2, int solidWidth2, int solidHeight2, int solidColor2, float timeStretch2, float startProgress2, int preCompWidth2, int preCompHeight2, List<Keyframe<Float>> inOutKeyframes2, MatteType matteType2) {
        this.shapes = shapes2;
        this.composition = composition2;
        this.layerName = layerName2;
        this.layerId = layerId2;
        this.layerType = layerType2;
        this.parentId = parentId2;
        this.refId = refId2;
        this.masks = masks2;
        this.transform = transform2;
        this.solidWidth = solidWidth2;
        this.solidHeight = solidHeight2;
        this.solidColor = solidColor2;
        this.timeStretch = timeStretch2;
        this.startProgress = startProgress2;
        this.preCompWidth = preCompWidth2;
        this.preCompHeight = preCompHeight2;
        this.inOutKeyframes = inOutKeyframes2;
        this.matteType = matteType2;
    }

    /* access modifiers changed from: 0000 */
    public float getStartProgress() {
        return this.startProgress;
    }

    /* access modifiers changed from: 0000 */
    public List<Keyframe<Float>> getInOutKeyframes() {
        return this.inOutKeyframes;
    }

    /* access modifiers changed from: 0000 */
    public long getId() {
        return this.layerId;
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return this.layerName;
    }

    /* access modifiers changed from: 0000 */
    public String getRefId() {
        return this.refId;
    }

    /* access modifiers changed from: 0000 */
    public List<Mask> getMasks() {
        return this.masks;
    }

    /* access modifiers changed from: 0000 */
    public LayerType getLayerType() {
        return this.layerType;
    }

    /* access modifiers changed from: 0000 */
    public MatteType getMatteType() {
        return this.matteType;
    }

    /* access modifiers changed from: 0000 */
    public long getParentId() {
        return this.parentId;
    }

    /* access modifiers changed from: 0000 */
    public List<Object> getShapes() {
        return this.shapes;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableTransform getTransform() {
        return this.transform;
    }

    /* access modifiers changed from: 0000 */
    public int getSolidColor() {
        return this.solidColor;
    }

    /* access modifiers changed from: 0000 */
    public int getSolidHeight() {
        return this.solidHeight;
    }

    /* access modifiers changed from: 0000 */
    public int getSolidWidth() {
        return this.solidWidth;
    }

    public String toString() {
        return toString("");
    }

    /* access modifiers changed from: 0000 */
    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(getName()).append("\n");
        Layer parent = this.composition.layerModelForId(getParentId());
        if (parent != null) {
            sb.append("\t\tParents: ").append(parent.getName());
            Layer parent2 = this.composition.layerModelForId(parent.getParentId());
            while (parent2 != null) {
                sb.append("->").append(parent2.getName());
                parent2 = this.composition.layerModelForId(parent2.getParentId());
            }
            sb.append(prefix).append("\n");
        }
        if (!getMasks().isEmpty()) {
            sb.append(prefix).append("\tMasks: ").append(getMasks().size()).append("\n");
        }
        if (!(getSolidWidth() == 0 || getSolidHeight() == 0)) {
            sb.append(prefix).append("\tBackground: ").append(String.format(Locale.US, "%dx%d %X\n", new Object[]{Integer.valueOf(getSolidWidth()), Integer.valueOf(getSolidHeight()), Integer.valueOf(getSolidColor())}));
        }
        if (!this.shapes.isEmpty()) {
            sb.append(prefix).append("\tShapes:\n");
            for (Object shape : this.shapes) {
                sb.append(prefix).append("\t\t").append(shape).append("\n");
            }
        }
        return sb.toString();
    }
}
