package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.LeftIconRowStyleApplier */
public final class LeftIconRowStyleApplier extends StyleApplier<LeftIconRowStyleApplier, LeftIconRow> {
    public LeftIconRowStyleApplier(LeftIconRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_LeftIconRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((LeftIconRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_LeftIconRow_n2_titleText)) {
            ((LeftIconRow) getView()).setTitle(a.getText(R.styleable.n2_LeftIconRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_LeftIconRow_n2_subtitleText)) {
            ((LeftIconRow) getView()).setSubtitle(a.getText(R.styleable.n2_LeftIconRow_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_LeftIconRow_n2_image)) {
            ((LeftIconRow) getView()).setImage(a.getResourceId(R.styleable.n2_LeftIconRow_n2_image, -1));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseComponentStyleApplier((BaseComponent) getView()).apply(style);
    }
}
