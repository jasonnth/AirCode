package com.airbnb.android.lib.reviews.fragments;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class FeedbackIntroFragment_ViewBinding implements Unbinder {
    private FeedbackIntroFragment target;

    public FeedbackIntroFragment_ViewBinding(FeedbackIntroFragment target2, View source) {
        this.target = target2;
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.document_marquee, "field 'documentMarquee'", DocumentMarquee.class);
        target2.aboutText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_about, "field 'aboutText'", TextView.class);
        target2.getStartedButton = Utils.findRequiredView(source, C0880R.C0882id.sticky_button, "field 'getStartedButton'");
        target2.listingHostRow = (UserDetailsActionRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.listing_host_row, "field 'listingHostRow'", UserDetailsActionRow.class);
        target2.listingImage = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.listing_image, "field 'listingImage'", AirImageView.class);
        target2.listingNameText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_title, "field 'listingNameText'", TextView.class);
    }

    public void unbind() {
        FeedbackIntroFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.documentMarquee = null;
        target2.aboutText = null;
        target2.getStartedButton = null;
        target2.listingHostRow = null;
        target2.listingImage = null;
        target2.listingNameText = null;
    }
}
