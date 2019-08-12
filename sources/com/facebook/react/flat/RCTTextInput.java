package com.facebook.react.flat;

import android.annotation.TargetApi;
import android.text.SpannableStringBuilder;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.view.MeasureUtil;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNodeAPI;

public class RCTTextInput extends RCTVirtualText implements AndroidView, YogaMeasureFunction {
    private EditText mEditText;
    private int mJsEventCount = -1;
    private int mNumberOfLines = -1;
    private boolean mPaddingChanged = false;
    private String mText;

    public /* bridge */ /* synthetic */ void addChildAt(ReactShadowNode reactShadowNode, int i) {
        super.addChildAt(reactShadowNode, i);
    }

    public /* bridge */ /* synthetic */ boolean clipsSubviews() {
        return super.clipsSubviews();
    }

    public /* bridge */ /* synthetic */ boolean isHorizontal() {
        return super.isHorizontal();
    }

    public /* bridge */ /* synthetic */ void markUpdated() {
        super.markUpdated();
    }

    @ReactProp(defaultDouble = Double.NaN, name = "color")
    public /* bridge */ /* synthetic */ void setColor(double d) {
        super.setColor(d);
    }

    @ReactProp(name = "fontFamily")
    public /* bridge */ /* synthetic */ void setFontFamily(String str) {
        super.setFontFamily(str);
    }

    @ReactProp(defaultFloat = Float.NaN, name = "fontSize")
    public /* bridge */ /* synthetic */ void setFontSize(float f) {
        super.setFontSize(f);
    }

    @ReactProp(name = "fontStyle")
    public /* bridge */ /* synthetic */ void setFontStyle(String str) {
        super.setFontStyle(str);
    }

    @ReactProp(name = "fontWeight")
    public /* bridge */ /* synthetic */ void setFontWeight(String str) {
        super.setFontWeight(str);
    }

    public /* bridge */ /* synthetic */ void setOverflow(String str) {
        super.setOverflow(str);
    }

    @ReactProp(name = "textDecorationLine")
    public /* bridge */ /* synthetic */ void setTextDecorationLine(String str) {
        super.setTextDecorationLine(str);
    }

    @ReactProp(customType = "Color", defaultInt = 1426063360, name = "textShadowColor")
    public /* bridge */ /* synthetic */ void setTextShadowColor(int i) {
        super.setTextShadowColor(i);
    }

    @ReactProp(name = "textShadowOffset")
    public /* bridge */ /* synthetic */ void setTextShadowOffset(ReadableMap readableMap) {
        super.setTextShadowOffset(readableMap);
    }

    @ReactProp(name = "textShadowRadius")
    public /* bridge */ /* synthetic */ void setTextShadowRadius(float f) {
        super.setTextShadowRadius(f);
    }

    public RCTTextInput() {
        forceMountToView();
        setMeasureFunction(this);
    }

    /* access modifiers changed from: protected */
    public void notifyChanged(boolean shouldRemeasure) {
        super.notifyChanged(shouldRemeasure);
        markUpdated();
    }

    @TargetApi(17)
    public void setThemedContext(ThemedReactContext themedContext) {
        super.setThemedContext(themedContext);
        this.mEditText = new EditText(themedContext);
        this.mEditText.setLayoutParams(new LayoutParams(-2, -2));
        setDefaultPadding(4, (float) this.mEditText.getPaddingStart());
        setDefaultPadding(1, (float) this.mEditText.getPaddingTop());
        setDefaultPadding(5, (float) this.mEditText.getPaddingEnd());
        setDefaultPadding(3, (float) this.mEditText.getPaddingBottom());
    }

    public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        EditText editText = (EditText) Assertions.assertNotNull(this.mEditText);
        int fontSize = getFontSize();
        editText.setTextSize(0, fontSize == -1 ? (float) ((int) Math.ceil((double) PixelUtil.toPixelFromSP(14.0f))) : (float) fontSize);
        editText.setPadding((int) Math.ceil((double) getPadding(4)), (int) Math.ceil((double) getPadding(1)), (int) Math.ceil((double) getPadding(5)), (int) Math.ceil((double) getPadding(3)));
        if (this.mNumberOfLines != -1) {
            editText.setLines(this.mNumberOfLines);
        }
        editText.measure(MeasureUtil.getMeasureSpec(width, widthMode), MeasureUtil.getMeasureSpec(height, heightMode));
        return YogaMeasureOutput.make(editText.getMeasuredWidth(), editText.getMeasuredHeight());
    }

    public boolean isVirtual() {
        return false;
    }

    public boolean isVirtualAnchor() {
        return true;
    }

    public void setBackgroundColor(int backgroundColor) {
    }

    public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
        super.onCollectExtraUpdates(uiViewOperationQueue);
        if (this.mJsEventCount != -1) {
            uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), new ReactTextUpdate(getText(), this.mJsEventCount, false, getPadding(4), getPadding(1), getPadding(5), getPadding(3), -1));
        }
    }

    @ReactProp(name = "mostRecentEventCount")
    public void setMostRecentEventCount(int mostRecentEventCount) {
        this.mJsEventCount = mostRecentEventCount;
    }

    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(int numberOfLines) {
        this.mNumberOfLines = numberOfLines;
        notifyChanged(true);
    }

    @ReactProp(name = "text")
    public void setText(String text) {
        this.mText = text;
        notifyChanged(true);
    }

    public void setPadding(int spacingType, float padding) {
        super.setPadding(spacingType, padding);
        this.mPaddingChanged = true;
        dirty();
    }

    public boolean isPaddingChanged() {
        return this.mPaddingChanged;
    }

    public void resetPaddingChanged() {
        this.mPaddingChanged = false;
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldAllowEmptySpans() {
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean isEditable() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void performCollectText(SpannableStringBuilder builder) {
        if (this.mText != null) {
            builder.append(this.mText);
        }
        super.performCollectText(builder);
    }

    public boolean needsCustomLayoutForChildren() {
        return false;
    }
}
