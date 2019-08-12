package com.airbnb.android.explore.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.primitives.AirTextView;

public class FilterRemovalSuggestionCard_ViewBinding implements Unbinder {
    private FilterRemovalSuggestionCard target;

    public FilterRemovalSuggestionCard_ViewBinding(FilterRemovalSuggestionCard target2) {
        this(target2, target2);
    }

    public FilterRemovalSuggestionCard_ViewBinding(FilterRemovalSuggestionCard target2, View source) {
        this.target = target2;
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.title, "field 'titleView'", AirTextView.class);
        target2.descriptionView = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.description, "field 'descriptionView'", AirTextView.class);
        target2.carousel = (Carousel) Utils.findRequiredViewAsType(source, C0857R.C0859id.carousel, "field 'carousel'", Carousel.class);
        target2.sectionDivider = Utils.findRequiredView(source, C0857R.C0859id.section_divider, "field 'sectionDivider'");
    }

    public void unbind() {
        FilterRemovalSuggestionCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleView = null;
        target2.descriptionView = null;
        target2.carousel = null;
        target2.sectionDivider = null;
    }
}
