package com.airbnb.p027n2.components.fixed_footers;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;
import com.airbnb.paris.ViewStyleApplier;

/* renamed from: com.airbnb.n2.components.fixed_footers.FixedDualActionFooterStyleApplier */
public final class FixedDualActionFooterStyleApplier extends StyleApplier<FixedDualActionFooterStyleApplier, FixedDualActionFooter> {
    public FixedDualActionFooterStyleApplier(FixedDualActionFooter view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_FixedDualActionFooter;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((FixedDualActionFooter) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_FixedDualActionFooter_n2_dividerStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_FixedDualActionFooter_n2_dividerStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((FixedDualActionFooter) getView()).divider).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_FixedDualActionFooter_n2_buttonStyle)) {
            ((FixedDualActionFooter) getView()).setButtonStyle(a.getResourceId(R.styleable.n2_FixedDualActionFooter_n2_buttonStyle, -1));
        }
        if (a.hasValue(R.styleable.n2_FixedDualActionFooter_n2_buttonText)) {
            ((FixedDualActionFooter) getView()).setButtonText(a.getText(R.styleable.n2_FixedDualActionFooter_n2_buttonText));
        }
        if (a.hasValue(R.styleable.n2_FixedDualActionFooter_n2_secondaryButtonStyle)) {
            ((FixedDualActionFooter) getView()).setSecondaryButtonStyle(a.getResourceId(R.styleable.n2_FixedDualActionFooter_n2_secondaryButtonStyle, -1));
        }
        if (a.hasValue(R.styleable.n2_FixedDualActionFooter_n2_secondaryButtonText)) {
            ((FixedDualActionFooter) getView()).setSecondaryButtonText(a.getText(R.styleable.n2_FixedDualActionFooter_n2_secondaryButtonText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new ViewStyleApplier(getView()).apply(style);
    }

    public FixedDualActionFooterStyleApplier applyBabu() {
        return (FixedDualActionFooterStyleApplier) apply(R.style.n2_FixedDualActionFooter_Babu);
    }

    public FixedDualActionFooterStyleApplier applyWhite() {
        return (FixedDualActionFooterStyleApplier) apply(R.style.n2_FixedDualActionFooter_Jellyfish);
    }
}
