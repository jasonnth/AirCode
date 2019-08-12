package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.ListingDescription_ViewBinding */
public class ListingDescription_ViewBinding implements Unbinder {
    private ListingDescription target;

    public ListingDescription_ViewBinding(ListingDescription target2, View source) {
        this.target = target2;
        target2.translateText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.translate_text, "field 'translateText'", AirTextView.class);
        target2.summaryHeading = (AirTextView) Utils.findRequiredViewAsType(source, R.id.summary_heading, "field 'summaryHeading'", AirTextView.class);
        target2.summaryHighlight = (AirTextView) Utils.findRequiredViewAsType(source, R.id.summary_highlight, "field 'summaryHighlight'", AirTextView.class);
        target2.summaryText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.listing_summary, "field 'summaryText'", AirTextView.class);
        target2.spaceDescriptionHeading = (AirTextView) Utils.findRequiredViewAsType(source, R.id.space_heading, "field 'spaceDescriptionHeading'", AirTextView.class);
        target2.spaceDescriptionText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.listing_space_description, "field 'spaceDescriptionText'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        ListingDescription target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.translateText = null;
        target2.summaryHeading = null;
        target2.summaryHighlight = null;
        target2.summaryText = null;
        target2.spaceDescriptionHeading = null;
        target2.spaceDescriptionText = null;
        target2.divider = null;
    }
}
