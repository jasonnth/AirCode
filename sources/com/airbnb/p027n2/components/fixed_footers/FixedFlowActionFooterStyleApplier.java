package com.airbnb.p027n2.components.fixed_footers;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;
import com.airbnb.paris.ViewStyleApplier;

/* renamed from: com.airbnb.n2.components.fixed_footers.FixedFlowActionFooterStyleApplier */
public final class FixedFlowActionFooterStyleApplier extends StyleApplier<FixedFlowActionFooterStyleApplier, FixedFlowActionFooter> {
    public FixedFlowActionFooterStyleApplier(FixedFlowActionFooter view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_FixedFlowActionFooter;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((FixedFlowActionFooter) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_FixedFlowActionFooter_n2_dividerStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_FixedFlowActionFooter_n2_dividerStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((FixedFlowActionFooter) getView()).divider).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_FixedFlowActionFooter_n2_titleStyle)) {
            ((FixedFlowActionFooter) getView()).setTitleStyle(a.getResourceId(R.styleable.n2_FixedFlowActionFooter_n2_titleStyle, -1));
        }
        if (a.hasValue(R.styleable.n2_FixedFlowActionFooter_n2_titleText)) {
            ((FixedFlowActionFooter) getView()).setTitle(a.getText(R.styleable.n2_FixedFlowActionFooter_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_FixedFlowActionFooter_n2_subtitleStyle)) {
            ((FixedFlowActionFooter) getView()).setSubtitleStyle(a.getResourceId(R.styleable.n2_FixedFlowActionFooter_n2_subtitleStyle, -1));
        }
        if (a.hasValue(R.styleable.n2_FixedFlowActionFooter_n2_subtitleText)) {
            ((FixedFlowActionFooter) getView()).setSubtitle(a.getText(R.styleable.n2_FixedFlowActionFooter_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_FixedFlowActionFooter_n2_buttonStyle)) {
            ((FixedFlowActionFooter) getView()).setButtonStyle(a.getResourceId(R.styleable.n2_FixedFlowActionFooter_n2_buttonStyle, -1));
        }
        if (a.hasValue(R.styleable.n2_FixedFlowActionFooter_n2_buttonText)) {
            ((FixedFlowActionFooter) getView()).setButtonText(a.getText(R.styleable.n2_FixedFlowActionFooter_n2_buttonText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new ViewStyleApplier(getView()).apply(style);
    }

    public FixedFlowActionFooterStyleApplier applyBabu() {
        return (FixedFlowActionFooterStyleApplier) apply(R.style.n2_FixedFlowActionFooter_Babu);
    }

    public FixedFlowActionFooterStyleApplier applyWhite() {
        return (FixedFlowActionFooterStyleApplier) apply(R.style.n2_FixedFlowActionFooter_Jellyfish);
    }
}
