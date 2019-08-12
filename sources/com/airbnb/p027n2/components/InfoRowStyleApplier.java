package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.InfoRowStyleApplier */
public final class InfoRowStyleApplier extends StyleApplier<InfoRowStyleApplier, InfoRow> {
    public InfoRowStyleApplier(InfoRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_InfoRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((InfoRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_InfoRow_n2_titleText)) {
            ((InfoRow) getView()).setTitle(a.getText(R.styleable.n2_InfoRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_InfoRow_n2_subtitleText)) {
            ((InfoRow) getView()).setSubtitleText(a.getText(R.styleable.n2_InfoRow_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_InfoRow_n2_infoText)) {
            ((InfoRow) getView()).setInfo(a.getText(R.styleable.n2_InfoRow_n2_infoText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
