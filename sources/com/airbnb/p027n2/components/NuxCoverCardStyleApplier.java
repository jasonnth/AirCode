package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.NuxCoverCardStyleApplier */
public final class NuxCoverCardStyleApplier extends StyleApplier<NuxCoverCardStyleApplier, NuxCoverCard> {
    public NuxCoverCardStyleApplier(NuxCoverCard view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_NuxCoverCard;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((NuxCoverCard) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_NuxCoverCard_n2_titleText)) {
            ((NuxCoverCard) getView()).setTitle(a.getText(R.styleable.n2_NuxCoverCard_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_NuxCoverCard_n2_subtitleText)) {
            ((NuxCoverCard) getView()).setSubtitle(a.getText(R.styleable.n2_NuxCoverCard_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_NuxCoverCard_n2_image)) {
            ((NuxCoverCard) getView()).setImage(a.getResourceId(R.styleable.n2_NuxCoverCard_n2_image, -1));
        }
        if (a.hasValue(R.styleable.n2_NuxCoverCard_n2_buttonText)) {
            ((NuxCoverCard) getView()).setButton(a.getText(R.styleable.n2_NuxCoverCard_n2_buttonText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseComponentStyleApplier((BaseComponent) getView()).apply(style);
    }
}
