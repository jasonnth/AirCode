package com.airbnb.android.explore.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.primitives.AirTextView;

public class FilterSuggestionCard_ViewBinding implements Unbinder {
    private FilterSuggestionCard target;

    public FilterSuggestionCard_ViewBinding(FilterSuggestionCard target2) {
        this(target2, target2);
    }

    public FilterSuggestionCard_ViewBinding(FilterSuggestionCard target2, View source) {
        this.target = target2;
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.title, "field 'titleView'", AirTextView.class);
        target2.carousel = (Carousel) Utils.findRequiredViewAsType(source, C0857R.C0859id.carousel, "field 'carousel'", Carousel.class);
    }

    public void unbind() {
        FilterSuggestionCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleView = null;
        target2.carousel = null;
    }
}
