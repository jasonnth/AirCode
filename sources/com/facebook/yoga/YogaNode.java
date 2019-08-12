package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;
import java.util.ArrayList;
import java.util.List;

@DoNotStrip
public class YogaNode implements YogaNodeAPI<YogaNode> {
    private List<YogaNode> mChildren;
    private Object mData;
    private boolean mHasSetBorder = false;
    private boolean mHasSetMargin = false;
    private boolean mHasSetPadding = false;
    private boolean mHasSetPosition = false;
    @DoNotStrip
    private float mHeight = Float.NaN;
    @DoNotStrip
    private int mLayoutDirection = 0;
    @DoNotStrip
    private float mLeft = Float.NaN;
    private YogaMeasureFunction mMeasureFunction;
    private long mNativePointer = jni_YGNodeNew();
    private YogaNode mParent;
    @DoNotStrip
    private float mTop = Float.NaN;
    @DoNotStrip
    private float mWidth = Float.NaN;

    private static native boolean jni_YGIsExperimentalFeatureEnabled(int i);

    static native void jni_YGLog(int i, String str);

    private native void jni_YGNodeCalculateLayout(long j);

    private native void jni_YGNodeCopyStyle(long j, long j2);

    private native void jni_YGNodeFree(long j);

    static native int jni_YGNodeGetInstanceCount();

    private native boolean jni_YGNodeHasNewLayout(long j);

    private native void jni_YGNodeInsertChild(long j, long j2, int i);

    private native boolean jni_YGNodeIsDirty(long j);

    private native void jni_YGNodeMarkDirty(long j);

    private native void jni_YGNodeMarkLayoutSeen(long j);

    private native long jni_YGNodeNew();

    private native void jni_YGNodeRemoveChild(long j, long j2);

    private native void jni_YGNodeReset(long j);

    private native void jni_YGNodeSetHasMeasureFunc(long j, boolean z);

    private native int jni_YGNodeStyleGetAlignContent(long j);

    private native int jni_YGNodeStyleGetAlignItems(long j);

    private native int jni_YGNodeStyleGetAlignSelf(long j);

    private native float jni_YGNodeStyleGetAspectRatio(long j);

    private native float jni_YGNodeStyleGetBorder(long j, int i);

    private native int jni_YGNodeStyleGetDirection(long j);

    private native Object jni_YGNodeStyleGetFlexBasis(long j);

    private native int jni_YGNodeStyleGetFlexDirection(long j);

    private native float jni_YGNodeStyleGetFlexGrow(long j);

    private native float jni_YGNodeStyleGetFlexShrink(long j);

    private native Object jni_YGNodeStyleGetHeight(long j);

    private native int jni_YGNodeStyleGetJustifyContent(long j);

    private native Object jni_YGNodeStyleGetMargin(long j, int i);

    private native Object jni_YGNodeStyleGetMaxHeight(long j);

    private native Object jni_YGNodeStyleGetMaxWidth(long j);

    private native Object jni_YGNodeStyleGetMinHeight(long j);

    private native Object jni_YGNodeStyleGetMinWidth(long j);

    private native int jni_YGNodeStyleGetOverflow(long j);

    private native Object jni_YGNodeStyleGetPadding(long j, int i);

    private native Object jni_YGNodeStyleGetPosition(long j, int i);

    private native int jni_YGNodeStyleGetPositionType(long j);

    private native Object jni_YGNodeStyleGetWidth(long j);

    private native void jni_YGNodeStyleSetAlignContent(long j, int i);

    private native void jni_YGNodeStyleSetAlignItems(long j, int i);

    private native void jni_YGNodeStyleSetAlignSelf(long j, int i);

    private native void jni_YGNodeStyleSetAspectRatio(long j, float f);

    private native void jni_YGNodeStyleSetBorder(long j, int i, float f);

    private native void jni_YGNodeStyleSetDirection(long j, int i);

    private native void jni_YGNodeStyleSetFlex(long j, float f);

    private native void jni_YGNodeStyleSetFlexBasis(long j, float f);

    private native void jni_YGNodeStyleSetFlexBasisPercent(long j, float f);

    private native void jni_YGNodeStyleSetFlexDirection(long j, int i);

    private native void jni_YGNodeStyleSetFlexGrow(long j, float f);

    private native void jni_YGNodeStyleSetFlexShrink(long j, float f);

    private native void jni_YGNodeStyleSetFlexWrap(long j, int i);

    private native void jni_YGNodeStyleSetHeight(long j, float f);

    private native void jni_YGNodeStyleSetHeightPercent(long j, float f);

    private native void jni_YGNodeStyleSetJustifyContent(long j, int i);

    private native void jni_YGNodeStyleSetMargin(long j, int i, float f);

    private native void jni_YGNodeStyleSetMarginPercent(long j, int i, float f);

    private native void jni_YGNodeStyleSetMaxHeight(long j, float f);

    private native void jni_YGNodeStyleSetMaxHeightPercent(long j, float f);

    private native void jni_YGNodeStyleSetMaxWidth(long j, float f);

    private native void jni_YGNodeStyleSetMaxWidthPercent(long j, float f);

    private native void jni_YGNodeStyleSetMinHeight(long j, float f);

    private native void jni_YGNodeStyleSetMinHeightPercent(long j, float f);

    private native void jni_YGNodeStyleSetMinWidth(long j, float f);

    private native void jni_YGNodeStyleSetMinWidthPercent(long j, float f);

    private native void jni_YGNodeStyleSetOverflow(long j, int i);

    private native void jni_YGNodeStyleSetPadding(long j, int i, float f);

    private native void jni_YGNodeStyleSetPaddingPercent(long j, int i, float f);

    private native void jni_YGNodeStyleSetPosition(long j, int i, float f);

    private native void jni_YGNodeStyleSetPositionPercent(long j, int i, float f);

    private native void jni_YGNodeStyleSetPositionType(long j, int i);

    private native void jni_YGNodeStyleSetWidth(long j, float f);

    private native void jni_YGNodeStyleSetWidthPercent(long j, float f);

    private static native void jni_YGSetExperimentalFeatureEnabled(int i, boolean z);

    private static native void jni_YGSetLogger(Object obj);

    static {
        SoLoader.loadLibrary("yoga");
    }

    public static void setLogger(YogaLogger logger) {
        jni_YGSetLogger(logger);
    }

    public static void setExperimentalFeatureEnabled(YogaExperimentalFeature feature, boolean enabled) {
        jni_YGSetExperimentalFeatureEnabled(feature.intValue(), enabled);
    }

    public static boolean isExperimentalFeatureEnabled(YogaExperimentalFeature feature) {
        return jni_YGIsExperimentalFeatureEnabled(feature.intValue());
    }

    public YogaNode() {
        if (this.mNativePointer == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            jni_YGNodeFree(this.mNativePointer);
        } finally {
            super.finalize();
        }
    }

    public void reset() {
        this.mHasSetPadding = false;
        this.mHasSetMargin = false;
        this.mHasSetBorder = false;
        this.mHasSetPosition = false;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mLayoutDirection = 0;
        this.mMeasureFunction = null;
        this.mData = null;
        jni_YGNodeReset(this.mNativePointer);
    }

    public int getChildCount() {
        if (this.mChildren == null) {
            return 0;
        }
        return this.mChildren.size();
    }

    public YogaNode getChildAt(int i) {
        return (YogaNode) this.mChildren.get(i);
    }

    public void addChildAt(YogaNode child, int i) {
        if (child.mParent != null) {
            throw new IllegalStateException("Child already has a parent, it must be removed first.");
        }
        if (this.mChildren == null) {
            this.mChildren = new ArrayList(4);
        }
        this.mChildren.add(i, child);
        child.mParent = this;
        jni_YGNodeInsertChild(this.mNativePointer, child.mNativePointer, i);
    }

    public YogaNode removeChildAt(int i) {
        YogaNode child = (YogaNode) this.mChildren.remove(i);
        child.mParent = null;
        jni_YGNodeRemoveChild(this.mNativePointer, child.mNativePointer);
        return child;
    }

    public YogaNode getParent() {
        return this.mParent;
    }

    public int indexOf(YogaNode child) {
        if (this.mChildren == null) {
            return -1;
        }
        return this.mChildren.indexOf(child);
    }

    public void calculateLayout() {
        jni_YGNodeCalculateLayout(this.mNativePointer);
    }

    public boolean hasNewLayout() {
        return jni_YGNodeHasNewLayout(this.mNativePointer);
    }

    public void dirty() {
        jni_YGNodeMarkDirty(this.mNativePointer);
    }

    public boolean isDirty() {
        return jni_YGNodeIsDirty(this.mNativePointer);
    }

    public void markLayoutSeen() {
        jni_YGNodeMarkLayoutSeen(this.mNativePointer);
    }

    public void copyStyle(YogaNode srcNode) {
        jni_YGNodeCopyStyle(this.mNativePointer, srcNode.mNativePointer);
    }

    public YogaDirection getStyleDirection() {
        return YogaDirection.values()[jni_YGNodeStyleGetDirection(this.mNativePointer)];
    }

    public void setDirection(YogaDirection direction) {
        jni_YGNodeStyleSetDirection(this.mNativePointer, direction.intValue());
    }

    public YogaFlexDirection getFlexDirection() {
        return YogaFlexDirection.values()[jni_YGNodeStyleGetFlexDirection(this.mNativePointer)];
    }

    public void setFlexDirection(YogaFlexDirection flexDirection) {
        jni_YGNodeStyleSetFlexDirection(this.mNativePointer, flexDirection.intValue());
    }

    public YogaJustify getJustifyContent() {
        return YogaJustify.values()[jni_YGNodeStyleGetJustifyContent(this.mNativePointer)];
    }

    public void setJustifyContent(YogaJustify justifyContent) {
        jni_YGNodeStyleSetJustifyContent(this.mNativePointer, justifyContent.intValue());
    }

    public YogaAlign getAlignItems() {
        return YogaAlign.values()[jni_YGNodeStyleGetAlignItems(this.mNativePointer)];
    }

    public void setAlignItems(YogaAlign alignItems) {
        jni_YGNodeStyleSetAlignItems(this.mNativePointer, alignItems.intValue());
    }

    public YogaAlign getAlignSelf() {
        return YogaAlign.values()[jni_YGNodeStyleGetAlignSelf(this.mNativePointer)];
    }

    public void setAlignSelf(YogaAlign alignSelf) {
        jni_YGNodeStyleSetAlignSelf(this.mNativePointer, alignSelf.intValue());
    }

    public YogaAlign getAlignContent() {
        return YogaAlign.values()[jni_YGNodeStyleGetAlignContent(this.mNativePointer)];
    }

    public void setAlignContent(YogaAlign alignContent) {
        jni_YGNodeStyleSetAlignContent(this.mNativePointer, alignContent.intValue());
    }

    public YogaPositionType getPositionType() {
        return YogaPositionType.values()[jni_YGNodeStyleGetPositionType(this.mNativePointer)];
    }

    public void setPositionType(YogaPositionType positionType) {
        jni_YGNodeStyleSetPositionType(this.mNativePointer, positionType.intValue());
    }

    public void setWrap(YogaWrap flexWrap) {
        jni_YGNodeStyleSetFlexWrap(this.mNativePointer, flexWrap.intValue());
    }

    public YogaOverflow getOverflow() {
        return YogaOverflow.values()[jni_YGNodeStyleGetOverflow(this.mNativePointer)];
    }

    public void setOverflow(YogaOverflow overflow) {
        jni_YGNodeStyleSetOverflow(this.mNativePointer, overflow.intValue());
    }

    public void setFlex(float flex) {
        jni_YGNodeStyleSetFlex(this.mNativePointer, flex);
    }

    public float getFlexGrow() {
        return jni_YGNodeStyleGetFlexGrow(this.mNativePointer);
    }

    public void setFlexGrow(float flexGrow) {
        jni_YGNodeStyleSetFlexGrow(this.mNativePointer, flexGrow);
    }

    public float getFlexShrink() {
        return jni_YGNodeStyleGetFlexShrink(this.mNativePointer);
    }

    public void setFlexShrink(float flexShrink) {
        jni_YGNodeStyleSetFlexShrink(this.mNativePointer, flexShrink);
    }

    public YogaValue getFlexBasis() {
        return (YogaValue) jni_YGNodeStyleGetFlexBasis(this.mNativePointer);
    }

    public void setFlexBasis(float flexBasis) {
        jni_YGNodeStyleSetFlexBasis(this.mNativePointer, flexBasis);
    }

    public void setFlexBasisPercent(float percent) {
        jni_YGNodeStyleSetFlexBasisPercent(this.mNativePointer, percent);
    }

    public YogaValue getMargin(YogaEdge edge) {
        if (!this.mHasSetMargin) {
            return edge.intValue() < YogaEdge.START.intValue() ? YogaValue.ZERO : YogaValue.UNDEFINED;
        }
        return (YogaValue) jni_YGNodeStyleGetMargin(this.mNativePointer, edge.intValue());
    }

    public void setMargin(YogaEdge edge, float margin) {
        this.mHasSetMargin = true;
        jni_YGNodeStyleSetMargin(this.mNativePointer, edge.intValue(), margin);
    }

    public void setMarginPercent(YogaEdge edge, float percent) {
        this.mHasSetMargin = true;
        jni_YGNodeStyleSetMarginPercent(this.mNativePointer, edge.intValue(), percent);
    }

    public YogaValue getPadding(YogaEdge edge) {
        if (!this.mHasSetPadding) {
            return edge.intValue() < YogaEdge.START.intValue() ? YogaValue.ZERO : YogaValue.UNDEFINED;
        }
        return (YogaValue) jni_YGNodeStyleGetPadding(this.mNativePointer, edge.intValue());
    }

    public void setPadding(YogaEdge edge, float padding) {
        this.mHasSetPadding = true;
        jni_YGNodeStyleSetPadding(this.mNativePointer, edge.intValue(), padding);
    }

    public void setPaddingPercent(YogaEdge edge, float percent) {
        this.mHasSetPadding = true;
        jni_YGNodeStyleSetPaddingPercent(this.mNativePointer, edge.intValue(), percent);
    }

    public float getBorder(YogaEdge edge) {
        if (!this.mHasSetBorder) {
            return edge.intValue() < YogaEdge.START.intValue() ? 0.0f : Float.NaN;
        }
        return jni_YGNodeStyleGetBorder(this.mNativePointer, edge.intValue());
    }

    public void setBorder(YogaEdge edge, float border) {
        this.mHasSetBorder = true;
        jni_YGNodeStyleSetBorder(this.mNativePointer, edge.intValue(), border);
    }

    public YogaValue getPosition(YogaEdge edge) {
        if (!this.mHasSetPosition) {
            return YogaValue.UNDEFINED;
        }
        return (YogaValue) jni_YGNodeStyleGetPosition(this.mNativePointer, edge.intValue());
    }

    public void setPosition(YogaEdge edge, float position) {
        this.mHasSetPosition = true;
        jni_YGNodeStyleSetPosition(this.mNativePointer, edge.intValue(), position);
    }

    public void setPositionPercent(YogaEdge edge, float percent) {
        this.mHasSetPosition = true;
        jni_YGNodeStyleSetPositionPercent(this.mNativePointer, edge.intValue(), percent);
    }

    public YogaValue getWidth() {
        return (YogaValue) jni_YGNodeStyleGetWidth(this.mNativePointer);
    }

    public void setWidth(float width) {
        jni_YGNodeStyleSetWidth(this.mNativePointer, width);
    }

    public void setWidthPercent(float percent) {
        jni_YGNodeStyleSetWidthPercent(this.mNativePointer, percent);
    }

    public YogaValue getHeight() {
        return (YogaValue) jni_YGNodeStyleGetHeight(this.mNativePointer);
    }

    public void setHeight(float height) {
        jni_YGNodeStyleSetHeight(this.mNativePointer, height);
    }

    public void setHeightPercent(float percent) {
        jni_YGNodeStyleSetHeightPercent(this.mNativePointer, percent);
    }

    public YogaValue getMinWidth() {
        return (YogaValue) jni_YGNodeStyleGetMinWidth(this.mNativePointer);
    }

    public void setMinWidth(float minWidth) {
        jni_YGNodeStyleSetMinWidth(this.mNativePointer, minWidth);
    }

    public void setMinWidthPercent(float percent) {
        jni_YGNodeStyleSetMinWidthPercent(this.mNativePointer, percent);
    }

    public YogaValue getMinHeight() {
        return (YogaValue) jni_YGNodeStyleGetMinHeight(this.mNativePointer);
    }

    public void setMinHeight(float minHeight) {
        jni_YGNodeStyleSetMinHeight(this.mNativePointer, minHeight);
    }

    public void setMinHeightPercent(float percent) {
        jni_YGNodeStyleSetMinHeightPercent(this.mNativePointer, percent);
    }

    public YogaValue getMaxWidth() {
        return (YogaValue) jni_YGNodeStyleGetMaxWidth(this.mNativePointer);
    }

    public void setMaxWidth(float maxWidth) {
        jni_YGNodeStyleSetMaxWidth(this.mNativePointer, maxWidth);
    }

    public void setMaxWidthPercent(float percent) {
        jni_YGNodeStyleSetMaxWidthPercent(this.mNativePointer, percent);
    }

    public YogaValue getMaxHeight() {
        return (YogaValue) jni_YGNodeStyleGetMaxHeight(this.mNativePointer);
    }

    public void setMaxHeight(float maxheight) {
        jni_YGNodeStyleSetMaxHeight(this.mNativePointer, maxheight);
    }

    public void setMaxHeightPercent(float percent) {
        jni_YGNodeStyleSetMaxHeightPercent(this.mNativePointer, percent);
    }

    public float getAspectRatio() {
        return jni_YGNodeStyleGetAspectRatio(this.mNativePointer);
    }

    public void setAspectRatio(float aspectRatio) {
        jni_YGNodeStyleSetAspectRatio(this.mNativePointer, aspectRatio);
    }

    public float getLayoutX() {
        return this.mLeft;
    }

    public float getLayoutY() {
        return this.mTop;
    }

    public float getLayoutWidth() {
        return this.mWidth;
    }

    public float getLayoutHeight() {
        return this.mHeight;
    }

    public YogaDirection getLayoutDirection() {
        return YogaDirection.values()[this.mLayoutDirection];
    }

    public void setMeasureFunction(YogaMeasureFunction measureFunction) {
        this.mMeasureFunction = measureFunction;
        jni_YGNodeSetHasMeasureFunc(this.mNativePointer, measureFunction != null);
    }

    @DoNotStrip
    public final long measure(float width, int widthMode, float height, int heightMode) {
        if (!isMeasureDefined()) {
            throw new RuntimeException("Measure function isn't defined!");
        }
        return this.mMeasureFunction.measure(this, width, YogaMeasureMode.values()[widthMode], height, YogaMeasureMode.values()[heightMode]);
    }

    public boolean isMeasureDefined() {
        return this.mMeasureFunction != null;
    }

    public void setData(Object data) {
        this.mData = data;
    }

    public Object getData() {
        return this.mData;
    }
}
