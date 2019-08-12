package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.SummaryInterstitialStyleApplier */
public final class SummaryInterstitialStyleApplier extends StyleApplier<SummaryInterstitialStyleApplier, SummaryInterstitial> {
    public SummaryInterstitialStyleApplier(SummaryInterstitial view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_SummaryInterstitial;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((SummaryInterstitial) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_SummaryInterstitial_n2_titleText)) {
            ((SummaryInterstitial) getView()).setTitle(a.getText(R.styleable.n2_SummaryInterstitial_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_SummaryInterstitial_n2_subtitleText)) {
            ((SummaryInterstitial) getView()).setSubtitle(a.getText(R.styleable.n2_SummaryInterstitial_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_SummaryInterstitial_n2_thirdRowText)) {
            ((SummaryInterstitial) getView()).setThirdRowText(a.getText(R.styleable.n2_SummaryInterstitial_n2_thirdRowText));
        }
        if (a.hasValue(R.styleable.n2_SummaryInterstitial_n2_firstButtonText)) {
            ((SummaryInterstitial) getView()).setFirstButtonText(a.getText(R.styleable.n2_SummaryInterstitial_n2_firstButtonText));
        }
        if (a.hasValue(R.styleable.n2_SummaryInterstitial_n2_secondButtonText)) {
            ((SummaryInterstitial) getView()).setSecondButtonText(a.getText(R.styleable.n2_SummaryInterstitial_n2_secondButtonText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseComponentStyleApplier((BaseComponent) getView()).apply(style);
    }
}
