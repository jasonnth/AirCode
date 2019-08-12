package com.airbnb.p027n2.components.fixed_footers;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.fixed_footers.FixedFlowActionAdvanceFooterStyleApplier */
public final class FixedFlowActionAdvanceFooterStyleApplier extends StyleApplier<FixedFlowActionAdvanceFooterStyleApplier, FixedFlowActionAdvanceFooter> {
    public FixedFlowActionAdvanceFooterStyleApplier(FixedFlowActionAdvanceFooter view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_FixedFlowActionAdvanceFooter;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((FixedFlowActionAdvanceFooter) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_FixedFlowActionAdvanceFooter_n2_collapse)) {
            ((FixedFlowActionAdvanceFooter) getView()).collapse(a.getBoolean(R.styleable.n2_FixedFlowActionAdvanceFooter_n2_collapse, false));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new FixedFlowActionFooterStyleApplier((FixedFlowActionFooter) getView()).apply(style);
    }

    public FixedFlowActionAdvanceFooterStyleApplier applyCollapsedRausch() {
        return (FixedFlowActionAdvanceFooterStyleApplier) apply(R.style.n2_FixedFlowActionAdvanceFooter_Collapsed_Rausch);
    }

    public FixedFlowActionAdvanceFooterStyleApplier applyBabu() {
        return (FixedFlowActionAdvanceFooterStyleApplier) apply(R.style.n2_FixedFlowActionAdvanceFooter_Babu);
    }

    public FixedFlowActionAdvanceFooterStyleApplier applyCollapsedBabu() {
        return (FixedFlowActionAdvanceFooterStyleApplier) apply(R.style.n2_FixedFlowActionAdvanceFooter_Collapsed_Babu);
    }

    public FixedFlowActionAdvanceFooterStyleApplier applyWhite() {
        return (FixedFlowActionAdvanceFooterStyleApplier) apply(R.style.n2_FixedFlowActionAdvanceFooter_Jellyfish);
    }

    public FixedFlowActionAdvanceFooterStyleApplier applyCollapsedWhite() {
        return (FixedFlowActionAdvanceFooterStyleApplier) apply(R.style.n2_FixedFlowActionAdvanceFooter_Collapsed_Jellyfish);
    }
}
