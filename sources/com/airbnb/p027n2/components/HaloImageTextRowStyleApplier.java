package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.HaloImageTextRowStyleApplier */
public final class HaloImageTextRowStyleApplier extends StyleApplier<HaloImageTextRowStyleApplier, HaloImageTextRow> {
    public HaloImageTextRowStyleApplier(HaloImageTextRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_HaloImageTextRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((HaloImageTextRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_HaloImageTextRow_n2_firstRowText)) {
            ((HaloImageTextRow) getView()).setFirstTextRow(a.getText(R.styleable.n2_HaloImageTextRow_n2_firstRowText));
        }
        if (a.hasValue(R.styleable.n2_HaloImageTextRow_n2_secondRowText)) {
            ((HaloImageTextRow) getView()).setSecondTextRow(a.getText(R.styleable.n2_HaloImageTextRow_n2_secondRowText));
        }
        if (a.hasValue(R.styleable.n2_HaloImageTextRow_n2_imageUrl)) {
            ((HaloImageTextRow) getView()).setHaloImageUrl(a.getString(R.styleable.n2_HaloImageTextRow_n2_imageUrl));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
