package com.airbnb.android.listing.views;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.listing.C7213R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

public class TipView_ViewBinding implements Unbinder {
    private TipView target;

    public TipView_ViewBinding(TipView target2) {
        this(target2, target2);
    }

    public TipView_ViewBinding(TipView target2, View source) {
        this.target = target2;
        target2.tipContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C7213R.C7215id.tip_container, "field 'tipContainer'", LinearLayout.class);
        target2.tipTextView = (AirTextView) Utils.findRequiredViewAsType(source, C7213R.C7215id.tip_text, "field 'tipTextView'", AirTextView.class);
        target2.tipButton = (AirButton) Utils.findRequiredViewAsType(source, C7213R.C7215id.tip_button, "field 'tipButton'", AirButton.class);
    }

    public void unbind() {
        TipView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.tipContainer = null;
        target2.tipTextView = null;
        target2.tipButton = null;
    }
}
