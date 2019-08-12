package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.SectionHeaderStyleApplier */
public final class SectionHeaderStyleApplier extends StyleApplier<SectionHeaderStyleApplier, SectionHeader> {
    public SectionHeaderStyleApplier(SectionHeader view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_SectionHeader;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((SectionHeader) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_SectionHeader_n2_titleStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_SectionHeader_n2_titleStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((SectionHeader) getView()).titleView).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_SectionHeader_n2_buttonStyle)) {
            Style subStyle2 = new Style(a.getResourceId(R.styleable.n2_SectionHeader_n2_buttonStyle, -1));
            subStyle2.setDebugListener(style.getDebugListener());
            Paris.style(((SectionHeader) getView()).button).apply(subStyle2);
        }
        if (a.hasValue(R.styleable.n2_SectionHeader_n2_titleText)) {
            ((SectionHeader) getView()).setTitle(a.getText(R.styleable.n2_SectionHeader_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_SectionHeader_n2_descriptionText)) {
            ((SectionHeader) getView()).setDescription(a.getText(R.styleable.n2_SectionHeader_n2_descriptionText));
        }
        if (a.hasValue(R.styleable.n2_SectionHeader_n2_buttonText)) {
            ((SectionHeader) getView()).setButtonText(a.getText(R.styleable.n2_SectionHeader_n2_buttonText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }

    public SectionHeaderStyleApplier applyFirstBabuLink() {
        return (SectionHeaderStyleApplier) apply(R.style.n2_SectionHeader_First_BabuLink);
    }

    public SectionHeaderStyleApplier applySecondary() {
        return (SectionHeaderStyleApplier) apply(R.style.n2_SectionHeader_Secondary);
    }
}
