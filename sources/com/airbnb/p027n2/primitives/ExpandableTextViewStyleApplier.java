package com.airbnb.p027n2.primitives;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.primitives.ExpandableTextViewStyleApplier */
public final class ExpandableTextViewStyleApplier extends StyleApplier<ExpandableTextViewStyleApplier, ExpandableTextView> {
    public ExpandableTextViewStyleApplier(ExpandableTextView view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_ExpandableTextView;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((ExpandableTextView) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_ExpandableTextView_n2_expandable)) {
            ((ExpandableTextView) getView()).setExpandable(a.getBoolean(R.styleable.n2_ExpandableTextView_n2_expandable, false));
        }
        if (a.hasValue(R.styleable.n2_ExpandableTextView_n2_maxLines)) {
            ((ExpandableTextView) getView()).setMaxLines(a.getInt(R.styleable.n2_ExpandableTextView_n2_maxLines, -1));
        }
        if (a.hasValue(R.styleable.n2_ExpandableTextView_n2_contentText)) {
            ((ExpandableTextView) getView()).setContentText(a.getText(R.styleable.n2_ExpandableTextView_n2_contentText));
        }
        if (a.hasValue(R.styleable.n2_ExpandableTextView_n2_readMoreText)) {
            ((ExpandableTextView) getView()).setReadMoreText(a.getText(R.styleable.n2_ExpandableTextView_n2_readMoreText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new AirTextViewStyleApplier((AirTextView) getView()).apply(style);
    }
}
