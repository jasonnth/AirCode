package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.MultiLineSplitRowStyleApplier */
public final class MultiLineSplitRowStyleApplier extends StyleApplier<MultiLineSplitRowStyleApplier, MultiLineSplitRow> {
    public MultiLineSplitRowStyleApplier(MultiLineSplitRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_MultiLineSplitRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((MultiLineSplitRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_MultiLineSplitRow_n2_titleText)) {
            ((MultiLineSplitRow) getView()).setTitle(a.getText(R.styleable.n2_MultiLineSplitRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_MultiLineSplitRow_n2_infoText)) {
            ((MultiLineSplitRow) getView()).setInfoText(a.getText(R.styleable.n2_MultiLineSplitRow_n2_infoText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }

    public MultiLineSplitRowStyleApplier applyRegular() {
        return (MultiLineSplitRowStyleApplier) apply(R.style.n2_MultiLineSplitRow);
    }

    public MultiLineSplitRowStyleApplier applyNoTopPadding() {
        return (MultiLineSplitRowStyleApplier) apply(R.style.n2_MultiLineSplitRow_NoTopPadding);
    }
}
