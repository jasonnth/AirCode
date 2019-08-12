package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.BasicRowStyleApplier */
public final class BasicRowStyleApplier extends StyleApplier<BasicRowStyleApplier, BasicRow> {
    public BasicRowStyleApplier(BasicRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_BasicRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((BasicRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_BasicRow_n2_titleStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_BasicRow_n2_titleStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((BasicRow) getView()).titleText).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_BasicRow_n2_subtitleStyle)) {
            Style subStyle2 = new Style(a.getResourceId(R.styleable.n2_BasicRow_n2_subtitleStyle, -1));
            subStyle2.setDebugListener(style.getDebugListener());
            Paris.style(((BasicRow) getView()).subtitleText).apply(subStyle2);
        }
        if (a.hasValue(R.styleable.n2_BasicRow_n2_titleText)) {
            ((BasicRow) getView()).setTitle(a.getText(R.styleable.n2_BasicRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_BasicRow_n2_subtitleText)) {
            ((BasicRow) getView()).setSubtitleText(a.getText(R.styleable.n2_BasicRow_n2_subtitleText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
