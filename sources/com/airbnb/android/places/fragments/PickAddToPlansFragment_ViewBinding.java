package com.airbnb.android.places.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;

public class PickAddToPlansFragment_ViewBinding implements Unbinder {
    private PickAddToPlansFragment target;
    private View view2131755527;

    public PickAddToPlansFragment_ViewBinding(final PickAddToPlansFragment target2, View source) {
        this.target = target2;
        target2.mainRecyclerView = (Carousel) Utils.findRequiredViewAsType(source, C7627R.C7629id.add_to_plans_recycler_view, "field 'mainRecyclerView'", Carousel.class);
        View view = Utils.findRequiredView(source, C7627R.C7629id.primary_button, "field 'fixedActionFooter' and method 'clickButton'");
        target2.fixedActionFooter = (FixedActionFooter) Utils.castView(view, C7627R.C7629id.primary_button, "field 'fixedActionFooter'", FixedActionFooter.class);
        this.view2131755527 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickButton();
            }
        });
        target2.timeOfDayButtons = (Carousel) Utils.findRequiredViewAsType(source, C7627R.C7629id.time_of_day_buttons, "field 'timeOfDayButtons'", Carousel.class);
    }

    public void unbind() {
        PickAddToPlansFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mainRecyclerView = null;
        target2.fixedActionFooter = null;
        target2.timeOfDayButtons = null;
        this.view2131755527.setOnClickListener(null);
        this.view2131755527 = null;
    }
}
