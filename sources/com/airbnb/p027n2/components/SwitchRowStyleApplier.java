package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.SwitchRowStyleApplier */
public final class SwitchRowStyleApplier extends StyleApplier<SwitchRowStyleApplier, SwitchRow> {
    public SwitchRowStyleApplier(SwitchRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_SwitchRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((SwitchRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_SwitchRow_n2_titleStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_SwitchRow_n2_titleStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((SwitchRow) getView()).title).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_SwitchRow_n2_descriptionStyle)) {
            Style subStyle2 = new Style(a.getResourceId(R.styleable.n2_SwitchRow_n2_descriptionStyle, -1));
            subStyle2.setDebugListener(style.getDebugListener());
            Paris.style(((SwitchRow) getView()).description).apply(subStyle2);
        }
        if (a.hasValue(R.styleable.n2_SwitchRow_n2_switchStyleRes)) {
            Style subStyle3 = new Style(a.getResourceId(R.styleable.n2_SwitchRow_n2_switchStyleRes, -1));
            subStyle3.setDebugListener(style.getDebugListener());
            Paris.style(((SwitchRow) getView()).switchView).apply(subStyle3);
        }
        if (a.hasValue(R.styleable.n2_SwitchRow_n2_titleText)) {
            ((SwitchRow) getView()).setTitle(a.getText(R.styleable.n2_SwitchRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_SwitchRow_n2_descriptionText)) {
            ((SwitchRow) getView()).setDescription(a.getText(R.styleable.n2_SwitchRow_n2_descriptionText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }

    public SwitchRowStyleApplier applySheet() {
        return (SwitchRowStyleApplier) apply(R.style.n2_SwitchRow_Sheet);
    }
}
