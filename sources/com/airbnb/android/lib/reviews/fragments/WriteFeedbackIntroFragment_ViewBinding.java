package com.airbnb.android.lib.reviews.fragments;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.DocumentMarquee;

public class WriteFeedbackIntroFragment_ViewBinding implements Unbinder {
    private WriteFeedbackIntroFragment target;

    public WriteFeedbackIntroFragment_ViewBinding(WriteFeedbackIntroFragment target2, View source) {
        this.target = target2;
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.document_marquee, "field 'documentMarquee'", DocumentMarquee.class);
        target2.reviewText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_review_comments, "field 'reviewText'", TextView.class);
        target2.writeButton = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.write_review, "field 'writeButton'", TextView.class);
    }

    public void unbind() {
        WriteFeedbackIntroFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.documentMarquee = null;
        target2.reviewText = null;
        target2.writeButton = null;
    }
}
