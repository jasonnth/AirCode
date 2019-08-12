package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.FilterSuggestionPill_ViewBinding */
public class FilterSuggestionPill_ViewBinding implements Unbinder {
    private FilterSuggestionPill target;

    public FilterSuggestionPill_ViewBinding(FilterSuggestionPill target2, View source) {
        this.target = target2;
        target2.filterView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.filter, "field 'filterView'", AirTextView.class);
    }

    public void unbind() {
        FilterSuggestionPill target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.filterView = null;
    }
}
