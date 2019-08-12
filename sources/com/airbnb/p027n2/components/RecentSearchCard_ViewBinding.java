package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.RecentSearchCard_ViewBinding */
public class RecentSearchCard_ViewBinding implements Unbinder {
    private RecentSearchCard target;

    public RecentSearchCard_ViewBinding(RecentSearchCard target2, View source) {
        this.target = target2;
        target2.locationView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.location, "field 'locationView'", AirTextView.class);
        target2.subtitleView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitleView'", AirTextView.class);
    }

    public void unbind() {
        RecentSearchCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.locationView = null;
        target2.subtitleView = null;
    }
}
