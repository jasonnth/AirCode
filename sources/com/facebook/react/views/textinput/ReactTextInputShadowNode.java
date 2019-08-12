package com.facebook.react.views.textinput;

import android.os.Build.VERSION;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactTextShadowNode;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.view.MeasureUtil;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNodeAPI;

@VisibleForTesting
public class ReactTextInputShadowNode extends ReactTextShadowNode implements YogaMeasureFunction {
    private float[] mComputedPadding;
    private EditText mEditText;
    private int mJsEventCount = -1;

    public ReactTextInputShadowNode() {
        if (VERSION.SDK_INT < 23) {
        }
        this.mTextBreakStrategy = 0;
        setMeasureFunction(this);
    }

    public void setThemedContext(ThemedReactContext themedContext) {
        super.setThemedContext(themedContext);
        this.mEditText = new EditText(getThemedContext());
        this.mEditText.setLayoutParams(new LayoutParams(-2, -2));
        setDefaultPadding(4, (float) this.mEditText.getPaddingStart());
        setDefaultPadding(1, (float) this.mEditText.getPaddingTop());
        setDefaultPadding(5, (float) this.mEditText.getPaddingEnd());
        setDefaultPadding(3, (float) this.mEditText.getPaddingBottom());
        this.mComputedPadding = new float[]{getPadding(4), getPadding(1), getPadding(5), getPadding(3)};
    }

    public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        EditText editText = (EditText) Assertions.assertNotNull(this.mEditText);
        editText.setTextSize(0, this.mFontSize == -1 ? (float) ((int) Math.ceil((double) PixelUtil.toPixelFromSP(14.0f))) : (float) this.mFontSize);
        this.mComputedPadding = new float[]{getPadding(4), getPadding(1), getPadding(5), getPadding(3)};
        editText.setPadding((int) Math.floor((double) getPadding(4)), (int) Math.floor((double) getPadding(1)), (int) Math.floor((double) getPadding(5)), (int) Math.floor((double) getPadding(3)));
        if (VERSION.SDK_INT >= 21) {
            editText.setLetterSpacing(getLetterSpacing());
        }
        if (this.mNumberOfLines != -1) {
            editText.setLines(this.mNumberOfLines);
        }
        if (VERSION.SDK_INT >= 23 && editText.getBreakStrategy() != this.mTextBreakStrategy) {
            editText.setBreakStrategy(this.mTextBreakStrategy);
        }
        editText.measure(MeasureUtil.getMeasureSpec(width, widthMode), MeasureUtil.getMeasureSpec(height, heightMode));
        return YogaMeasureOutput.make(editText.getMeasuredWidth(), editText.getMeasuredHeight());
    }

    public void onBeforeLayout() {
    }

    @ReactProp(name = "mostRecentEventCount")
    public void setMostRecentEventCount(int mostRecentEventCount) {
        this.mJsEventCount = mostRecentEventCount;
    }

    public void setTextBreakStrategy(String textBreakStrategy) {
        if (VERSION.SDK_INT >= 23) {
            if (textBreakStrategy == null || "simple".equals(textBreakStrategy)) {
                this.mTextBreakStrategy = 0;
            } else if ("highQuality".equals(textBreakStrategy)) {
                this.mTextBreakStrategy = 1;
            } else if ("balanced".equals(textBreakStrategy)) {
                this.mTextBreakStrategy = 2;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textBreakStrategy: " + textBreakStrategy);
            }
        }
    }

    public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
        super.onCollectExtraUpdates(uiViewOperationQueue);
        if (this.mComputedPadding != null) {
            float[] updatedPadding = this.mComputedPadding;
            if (getLayoutDirection() == YogaDirection.RTL) {
                updatedPadding = new float[]{getPadding(5), getPadding(1), getPadding(4), getPadding(3)};
            }
            uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), updatedPadding);
            this.mComputedPadding = null;
        }
        if (this.mJsEventCount != -1) {
            uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), new ReactTextUpdate(fromTextCSSNode(this), this.mJsEventCount, this.mContainsImages, getPadding(4), getPadding(1), getPadding(5), getPadding(3), getLetterSpacing(), getFontSize(), this.mTextAlign, this.mTextBreakStrategy));
        }
    }

    public void setPadding(int spacingType, float padding) {
        super.setPadding(spacingType, padding);
        this.mComputedPadding = new float[]{getPadding(4), getPadding(1), getPadding(5), getPadding(3)};
        markUpdated();
    }
}
