package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButtonStyleApplier;
import com.airbnb.p027n2.primitives.AirTextViewStyleApplier;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;
import com.airbnb.paris.ViewStyleApplier;

/* renamed from: com.airbnb.n2.components.InterstitialStyleApplier */
public final class InterstitialStyleApplier extends StyleApplier<InterstitialStyleApplier, Interstitial> {
    public InterstitialStyleApplier(Interstitial view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_Interstitial;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((Interstitial) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_Interstitial_n2_titleStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_Interstitial_n2_titleStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((Interstitial) getView()).textView).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_Interstitial_n2_captionStyle)) {
            Style subStyle2 = new Style(a.getResourceId(R.styleable.n2_Interstitial_n2_captionStyle, -1));
            subStyle2.setDebugListener(style.getDebugListener());
            Paris.style(((Interstitial) getView()).captionView).apply(subStyle2);
        }
        if (a.hasValue(R.styleable.n2_Interstitial_n2_buttonStyle)) {
            Style subStyle3 = new Style(a.getResourceId(R.styleable.n2_Interstitial_n2_buttonStyle, -1));
            subStyle3.setDebugListener(style.getDebugListener());
            Paris.style(((Interstitial) getView()).button).apply(subStyle3);
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new ViewStyleApplier(getView()).apply(style);
    }

    public AirTextViewStyleApplier textView() {
        return new AirTextViewStyleApplier(((Interstitial) getView()).textView);
    }

    public AirTextViewStyleApplier captionView() {
        return new AirTextViewStyleApplier(((Interstitial) getView()).captionView);
    }

    public AirButtonStyleApplier button() {
        return new AirButtonStyleApplier(((Interstitial) getView()).button);
    }
}
