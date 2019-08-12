package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.ListingInfoRowStyleApplier */
public final class ListingInfoRowStyleApplier extends StyleApplier<ListingInfoRowStyleApplier, ListingInfoRow> {
    public ListingInfoRowStyleApplier(ListingInfoRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_ListingInfoRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((ListingInfoRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_ListingInfoRow_n2_buttonStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_ListingInfoRow_n2_buttonStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((ListingInfoRow) getView()).primaryButton).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_ListingInfoRow_n2_titleText)) {
            ((ListingInfoRow) getView()).setTitle(a.getText(R.styleable.n2_ListingInfoRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_ListingInfoRow_n2_subtitleText)) {
            ((ListingInfoRow) getView()).setSubtitleText(a.getText(R.styleable.n2_ListingInfoRow_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_ListingInfoRow_n2_buttonText)) {
            ((ListingInfoRow) getView()).setButtonText(a.getText(R.styleable.n2_ListingInfoRow_n2_buttonText));
        }
        if (a.hasValue(R.styleable.n2_ListingInfoRow_n2_labelText)) {
            ((ListingInfoRow) getView()).setLabel(a.getText(R.styleable.n2_ListingInfoRow_n2_labelText));
        }
        if (a.hasValue(R.styleable.n2_ListingInfoRow_n2_enabled)) {
            ((ListingInfoRow) getView()).setEnabled(a.getBoolean(R.styleable.n2_ListingInfoRow_n2_enabled, false));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }

    public ListingInfoRowStyleApplier applyDisabled() {
        return (ListingInfoRowStyleApplier) apply(R.style.n2_ListingInfoRow_Disabled);
    }

    public ListingInfoRowStyleApplier applyHackberry() {
        return (ListingInfoRowStyleApplier) apply(R.style.n2_ListingInfoRow_Hackberry);
    }
}
