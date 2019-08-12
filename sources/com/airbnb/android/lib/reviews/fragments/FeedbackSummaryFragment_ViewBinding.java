package com.airbnb.android.lib.reviews.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.EntryMarquee;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.components.UserDetailsActionRow;

public class FeedbackSummaryFragment_ViewBinding implements Unbinder {
    private FeedbackSummaryFragment target;

    public FeedbackSummaryFragment_ViewBinding(FeedbackSummaryFragment target2, View source) {
        this.target = target2;
        target2.marquee = (EntryMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.marquee, "field 'marquee'", EntryMarquee.class);
        target2.listingHostRow = (UserDetailsActionRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.listing_host_row, "field 'listingHostRow'", UserDetailsActionRow.class);
        target2.publicJumboRow = (StandardRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.public_feedback, "field 'publicJumboRow'", StandardRow.class);
        target2.privateJumboRow = (StandardRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.private_feedback, "field 'privateJumboRow'", StandardRow.class);
        target2.overallRating = (StandardRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.overall_rating, "field 'overallRating'", StandardRow.class);
        target2.recommendRating = (StandardRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.recommend_title, "field 'recommendRating'", StandardRow.class);
        target2.submitButton = (PrimaryButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.submit, "field 'submitButton'", PrimaryButton.class);
    }

    public void unbind() {
        FeedbackSummaryFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        target2.listingHostRow = null;
        target2.publicJumboRow = null;
        target2.privateJumboRow = null;
        target2.overallRating = null;
        target2.recommendRating = null;
        target2.submitButton = null;
    }
}
