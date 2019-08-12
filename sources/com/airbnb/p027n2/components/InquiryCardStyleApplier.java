package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.InquiryCardStyleApplier */
public final class InquiryCardStyleApplier extends StyleApplier<InquiryCardStyleApplier, InquiryCard> {
    public InquiryCardStyleApplier(InquiryCard view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_InquiryCard;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((InquiryCard) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_InquiryCard_n2_titleText)) {
            ((InquiryCard) getView()).setTitle(a.getText(R.styleable.n2_InquiryCard_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_InquiryCard_n2_subtitleText)) {
            ((InquiryCard) getView()).setSubtitle(a.getText(R.styleable.n2_InquiryCard_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_InquiryCard_n2_thirdRowText)) {
            ((InquiryCard) getView()).setThirdRowText(a.getText(R.styleable.n2_InquiryCard_n2_thirdRowText));
        }
        if (a.hasValue(R.styleable.n2_InquiryCard_n2_fourthRowText)) {
            ((InquiryCard) getView()).setFourthRowText(a.getText(R.styleable.n2_InquiryCard_n2_fourthRowText));
        }
        if (a.hasValue(R.styleable.n2_InquiryCard_n2_readMoreText)) {
            ((InquiryCard) getView()).setReadMoreText(a.getText(R.styleable.n2_InquiryCard_n2_readMoreText));
        }
        if (a.hasValue(R.styleable.n2_InquiryCard_n2_messageText)) {
            ((InquiryCard) getView()).setMessageText(a.getText(R.styleable.n2_InquiryCard_n2_messageText));
        }
        if (a.hasValue(R.styleable.n2_InquiryCard_n2_timeAgoText)) {
            ((InquiryCard) getView()).setTimeAgoText(a.getText(R.styleable.n2_InquiryCard_n2_timeAgoText));
        }
        if (a.hasValue(R.styleable.n2_InquiryCard_n2_showUnreadIndicator)) {
            ((InquiryCard) getView()).showUnreadIndicator(a.getBoolean(R.styleable.n2_InquiryCard_n2_showUnreadIndicator, false));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseComponentStyleApplier((BaseComponent) getView()).apply(style);
    }
}
