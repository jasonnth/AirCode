package com.airbnb.android.listing.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.listing.C7213R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SimpleTextRow;

public class ListingHostingFrequencyInfoFragment_ViewBinding implements Unbinder {
    private ListingHostingFrequencyInfoFragment target;

    public ListingHostingFrequencyInfoFragment_ViewBinding(ListingHostingFrequencyInfoFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7213R.C7215id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.textRow = (SimpleTextRow) Utils.findRequiredViewAsType(source, C7213R.C7215id.frequency_text, "field 'textRow'", SimpleTextRow.class);
    }

    public void unbind() {
        ListingHostingFrequencyInfoFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.textRow = null;
    }
}
