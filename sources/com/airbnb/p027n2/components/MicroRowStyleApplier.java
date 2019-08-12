package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.MicroRowStyleApplier */
public final class MicroRowStyleApplier extends StyleApplier<MicroRowStyleApplier, MicroRow> {
    public MicroRowStyleApplier(MicroRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_MicroRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((MicroRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_MicroRow_n2_text)) {
            ((MicroRow) getView()).setText(a.getText(R.styleable.n2_MicroRow_n2_text));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
