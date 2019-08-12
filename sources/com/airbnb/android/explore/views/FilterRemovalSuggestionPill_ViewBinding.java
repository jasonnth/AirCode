package com.airbnb.android.explore.views;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.primitives.AirTextView;

public class FilterRemovalSuggestionPill_ViewBinding implements Unbinder {
    private FilterRemovalSuggestionPill target;

    public FilterRemovalSuggestionPill_ViewBinding(FilterRemovalSuggestionPill target2) {
        this(target2, target2);
    }

    public FilterRemovalSuggestionPill_ViewBinding(FilterRemovalSuggestionPill target2, View source) {
        this.target = target2;
        target2.filterView = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.filter, "field 'filterView'", AirTextView.class);
        target2.closeButton = (ImageView) Utils.findRequiredViewAsType(source, C0857R.C0859id.close_button, "field 'closeButton'", ImageView.class);
    }

    public void unbind() {
        FilterRemovalSuggestionPill target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.filterView = null;
        target2.closeButton = null;
    }
}
