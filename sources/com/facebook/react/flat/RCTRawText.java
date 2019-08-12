package com.facebook.react.flat;

import android.text.SpannableStringBuilder;
import com.facebook.react.uimanager.annotations.ReactProp;

final class RCTRawText extends FlatTextShadowNode {
    private String mText;

    RCTRawText() {
    }

    /* access modifiers changed from: protected */
    public void performCollectText(SpannableStringBuilder builder) {
        if (this.mText != null) {
            builder.append(this.mText);
        }
    }

    /* access modifiers changed from: protected */
    public void performApplySpans(SpannableStringBuilder builder, int begin, int end, boolean isEditable) {
        builder.setSpan(this, begin, end, 17);
    }

    /* access modifiers changed from: protected */
    public void performCollectAttachDetachListeners(StateBuilder stateBuilder) {
    }

    @ReactProp(name = "text")
    public void setText(String text) {
        this.mText = text;
        notifyChanged(true);
    }
}
