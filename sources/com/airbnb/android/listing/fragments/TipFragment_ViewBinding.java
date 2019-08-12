package com.airbnb.android.listing.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.listing.C7213R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.TextRow;

public class TipFragment_ViewBinding implements Unbinder {
    private TipFragment target;

    public TipFragment_ViewBinding(TipFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7213R.C7215id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C7213R.C7215id.marquee, "field 'marquee'", DocumentMarquee.class);
        target2.text = (TextRow) Utils.findRequiredViewAsType(source, C7213R.C7215id.text, "field 'text'", TextRow.class);
    }

    public void unbind() {
        TipFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.marquee = null;
        target2.text = null;
    }
}
