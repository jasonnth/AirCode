package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.SearchParamsRow_ViewBinding */
public final class SearchParamsRow_ViewBinding implements Unbinder {
    private SearchParamsRow target;

    public SearchParamsRow_ViewBinding(SearchParamsRow target2, View source) {
        this.target = target2;
        target2.detailsText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.details_text, "field 'detailsText'", AirTextView.class);
        target2.locationText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.location_text, "field 'locationText'", AirTextView.class);
    }

    public void unbind() {
        SearchParamsRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.detailsText = null;
        target2.locationText = null;
    }
}
