package com.facebook.react.flat;

import android.text.SpannableStringBuilder;
import com.facebook.react.uimanager.ReactShadowNode;

abstract class FlatTextShadowNode extends FlatShadowNode {
    private int mTextBegin;
    private int mTextEnd;

    /* access modifiers changed from: protected */
    public abstract void performApplySpans(SpannableStringBuilder spannableStringBuilder, int i, int i2, boolean z);

    /* access modifiers changed from: protected */
    public abstract void performCollectAttachDetachListeners(StateBuilder stateBuilder);

    /* access modifiers changed from: protected */
    public abstract void performCollectText(SpannableStringBuilder spannableStringBuilder);

    FlatTextShadowNode() {
    }

    /* access modifiers changed from: protected */
    public void notifyChanged(boolean shouldRemeasure) {
        ReactShadowNode parent = getParent();
        if (parent instanceof FlatTextShadowNode) {
            ((FlatTextShadowNode) parent).notifyChanged(shouldRemeasure);
        }
    }

    public boolean isVirtual() {
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final void collectText(SpannableStringBuilder builder) {
        this.mTextBegin = builder.length();
        performCollectText(builder);
        this.mTextEnd = builder.length();
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldAllowEmptySpans() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean isEditable() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final void applySpans(SpannableStringBuilder builder, boolean isEditable) {
        if (this.mTextBegin != this.mTextEnd || shouldAllowEmptySpans()) {
            performApplySpans(builder, this.mTextBegin, this.mTextEnd, isEditable);
        }
    }
}
