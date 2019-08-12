package com.airbnb.android.cityregistration.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.listing.views.TipView;
import com.airbnb.p027n2.components.AirToolbar;

public class CityRegistrationDocReviewFragment_ViewBinding implements Unbinder {
    private CityRegistrationDocReviewFragment target;
    private View view2131755475;

    public CityRegistrationDocReviewFragment_ViewBinding(final CityRegistrationDocReviewFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5630R.C5632id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5630R.C5632id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.toolbarTitle = (TextView) Utils.findRequiredViewAsType(source, C5630R.C5632id.toolbar_title, "field 'toolbarTitle'", TextView.class);
        target2.tipView = (TipView) Utils.findRequiredViewAsType(source, C5630R.C5632id.tip, "field 'tipView'", TipView.class);
        View view = Utils.findRequiredView(source, C5630R.C5632id.save_button, "method 'onSave'");
        this.view2131755475 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSave();
            }
        });
    }

    public void unbind() {
        CityRegistrationDocReviewFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
        target2.toolbarTitle = null;
        target2.tipView = null;
        this.view2131755475.setOnClickListener(null);
        this.view2131755475 = null;
    }
}
