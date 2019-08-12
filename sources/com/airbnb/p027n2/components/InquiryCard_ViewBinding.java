package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.ExpandableTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.InquiryCard_ViewBinding */
public class InquiryCard_ViewBinding implements Unbinder {
    private InquiryCard target;

    public InquiryCard_ViewBinding(InquiryCard target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleText'", AirTextView.class);
        target2.timeAgoText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.time_ago, "field 'timeAgoText'", AirTextView.class);
        target2.unreadIndicator = (AirImageView) Utils.findRequiredViewAsType(source, R.id.unread_indicator, "field 'unreadIndicator'", AirImageView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle_text, "field 'subtitleText'", AirTextView.class);
        target2.thirdRowText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.third_row_text, "field 'thirdRowText'", AirTextView.class);
        target2.fourthRowText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.fourth_row_text, "field 'fourthRowText'", AirTextView.class);
        target2.messageText = (ExpandableTextView) Utils.findRequiredViewAsType(source, R.id.message, "field 'messageText'", ExpandableTextView.class);
    }

    public void unbind() {
        InquiryCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.timeAgoText = null;
        target2.unreadIndicator = null;
        target2.subtitleText = null;
        target2.thirdRowText = null;
        target2.fourthRowText = null;
        target2.messageText = null;
    }
}
