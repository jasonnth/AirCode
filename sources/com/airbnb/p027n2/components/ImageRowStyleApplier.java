package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.ImageRowStyleApplier */
public final class ImageRowStyleApplier extends StyleApplier<ImageRowStyleApplier, ImageRow> {
    public ImageRowStyleApplier(ImageRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_ImageRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((ImageRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_ImageRow_n2_titleText)) {
            ((ImageRow) getView()).setTitle(a.getText(R.styleable.n2_ImageRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_ImageRow_n2_subtitleText)) {
            ((ImageRow) getView()).setSubtitle(a.getText(R.styleable.n2_ImageRow_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_ImageRow_n2_image)) {
            ((ImageRow) getView()).setImage(a.getResourceId(R.styleable.n2_ImageRow_n2_image, -1));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
