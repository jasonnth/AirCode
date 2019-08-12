package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.LeftAlignedImageRowStyleApplier */
public final class LeftAlignedImageRowStyleApplier extends StyleApplier<LeftAlignedImageRowStyleApplier, LeftAlignedImageRow> {
    public LeftAlignedImageRowStyleApplier(LeftAlignedImageRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_LeftAlignedImageRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((LeftAlignedImageRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_LeftAlignedImageRow_n2_titleStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_LeftAlignedImageRow_n2_titleStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((LeftAlignedImageRow) getView()).titleText).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_LeftAlignedImageRow_n2_subtitleStyle)) {
            Style subStyle2 = new Style(a.getResourceId(R.styleable.n2_LeftAlignedImageRow_n2_subtitleStyle, -1));
            subStyle2.setDebugListener(style.getDebugListener());
            Paris.style(((LeftAlignedImageRow) getView()).subtitleText).apply(subStyle2);
        }
        if (a.hasValue(R.styleable.n2_LeftAlignedImageRow_n2_titleText)) {
            ((LeftAlignedImageRow) getView()).setTitle(a.getText(R.styleable.n2_LeftAlignedImageRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_LeftAlignedImageRow_n2_subtitleText)) {
            ((LeftAlignedImageRow) getView()).setSubtitle(a.getText(R.styleable.n2_LeftAlignedImageRow_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_LeftAlignedImageRow_n2_LeftAlignedImageStyle)) {
            ((LeftAlignedImageRow) getView()).setImageStyle(a.getResourceId(R.styleable.n2_LeftAlignedImageRow_n2_LeftAlignedImageStyle, -1));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
