package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.ValueRowStyleApplier */
public final class ValueRowStyleApplier extends StyleApplier<ValueRowStyleApplier, ValueRow> {
    public ValueRowStyleApplier(ValueRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_ValueRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((ValueRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_ValueRow_n2_titleStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_ValueRow_n2_titleStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((ValueRow) getView()).titleText).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_ValueRow_n2_subtitleStyle)) {
            Style subStyle2 = new Style(a.getResourceId(R.styleable.n2_ValueRow_n2_subtitleStyle, -1));
            subStyle2.setDebugListener(style.getDebugListener());
            Paris.style(((ValueRow) getView()).subtitleText).apply(subStyle2);
        }
        if (a.hasValue(R.styleable.n2_ValueRow_n2_valueStyle)) {
            Style subStyle3 = new Style(a.getResourceId(R.styleable.n2_ValueRow_n2_valueStyle, -1));
            subStyle3.setDebugListener(style.getDebugListener());
            Paris.style(((ValueRow) getView()).valueText).apply(subStyle3);
        }
        if (a.hasValue(R.styleable.n2_ValueRow_n2_titleText)) {
            ((ValueRow) getView()).setTitle(a.getText(R.styleable.n2_ValueRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_ValueRow_n2_subtitleText)) {
            ((ValueRow) getView()).setSubtitle(a.getText(R.styleable.n2_ValueRow_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_ValueRow_n2_valueText)) {
            ((ValueRow) getView()).setValue(a.getText(R.styleable.n2_ValueRow_n2_valueText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
