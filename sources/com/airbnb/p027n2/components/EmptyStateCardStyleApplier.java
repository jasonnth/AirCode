package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.EmptyStateCardStyleApplier */
public final class EmptyStateCardStyleApplier extends StyleApplier<EmptyStateCardStyleApplier, EmptyStateCard> {
    public EmptyStateCardStyleApplier(EmptyStateCard view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_EmptyStateCard;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((EmptyStateCard) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_EmptyStateCard_n2_text)) {
            ((EmptyStateCard) getView()).setText(a.getText(R.styleable.n2_EmptyStateCard_n2_text));
        }
        if (a.hasValue(R.styleable.n2_EmptyStateCard_n2_image)) {
            ((EmptyStateCard) getView()).setImage(a.getResourceId(R.styleable.n2_EmptyStateCard_n2_image, -1));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseComponentStyleApplier((BaseComponent) getView()).apply(style);
    }
}
