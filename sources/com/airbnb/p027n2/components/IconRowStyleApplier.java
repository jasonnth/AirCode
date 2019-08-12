package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.IconRowStyleApplier */
public final class IconRowStyleApplier extends StyleApplier<IconRowStyleApplier, IconRow> {
    public IconRowStyleApplier(IconRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_IconRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((IconRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_IconRow_n2_titleText)) {
            ((IconRow) getView()).setTitle(a.getText(R.styleable.n2_IconRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_IconRow_n2_subtitleText)) {
            ((IconRow) getView()).setSubtitleText(a.getText(R.styleable.n2_IconRow_n2_subtitleText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
