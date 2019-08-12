package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.ThreadPreviewRowWithLabelStyleApplier */
public final class ThreadPreviewRowWithLabelStyleApplier extends StyleApplier<ThreadPreviewRowWithLabelStyleApplier, ThreadPreviewRowWithLabel> {
    public ThreadPreviewRowWithLabelStyleApplier(ThreadPreviewRowWithLabel view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_ThreadPreviewRowWithLabel;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((ThreadPreviewRowWithLabel) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_ThreadPreviewRowWithLabel_n2_titleText)) {
            ((ThreadPreviewRowWithLabel) getView()).setTitle(a.getText(R.styleable.n2_ThreadPreviewRowWithLabel_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_ThreadPreviewRowWithLabel_n2_subtitleText)) {
            ((ThreadPreviewRowWithLabel) getView()).setSubtitle(a.getText(R.styleable.n2_ThreadPreviewRowWithLabel_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_ThreadPreviewRowWithLabel_n2_thirdRowText)) {
            ((ThreadPreviewRowWithLabel) getView()).setThirdRowText(a.getText(R.styleable.n2_ThreadPreviewRowWithLabel_n2_thirdRowText));
        }
        if (a.hasValue(R.styleable.n2_ThreadPreviewRowWithLabel_n2_fourthRowText)) {
            ((ThreadPreviewRowWithLabel) getView()).setFourthRowText(a.getText(R.styleable.n2_ThreadPreviewRowWithLabel_n2_fourthRowText));
        }
        if (a.hasValue(R.styleable.n2_ThreadPreviewRowWithLabel_n2_timeAgoText)) {
            ((ThreadPreviewRowWithLabel) getView()).setTimeAgoText(a.getText(R.styleable.n2_ThreadPreviewRowWithLabel_n2_timeAgoText));
        }
        if (a.hasValue(R.styleable.n2_ThreadPreviewRowWithLabel_n2_labelText)) {
            ((ThreadPreviewRowWithLabel) getView()).setLabelText(a.getText(R.styleable.n2_ThreadPreviewRowWithLabel_n2_labelText));
        }
        if (a.hasValue(R.styleable.n2_ThreadPreviewRowWithLabel_n2_image)) {
            ((ThreadPreviewRowWithLabel) getView()).setImage(a.getResourceId(R.styleable.n2_ThreadPreviewRowWithLabel_n2_image, -1));
        }
        if (a.hasValue(R.styleable.n2_ThreadPreviewRowWithLabel_n2_showUnreadIndicator)) {
            ((ThreadPreviewRowWithLabel) getView()).showUnreadIndicator(a.getBoolean(R.styleable.n2_ThreadPreviewRowWithLabel_n2_showUnreadIndicator, false));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
