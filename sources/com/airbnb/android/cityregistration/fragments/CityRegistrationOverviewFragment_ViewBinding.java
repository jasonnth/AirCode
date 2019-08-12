package com.airbnb.android.cityregistration.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class CityRegistrationOverviewFragment_ViewBinding implements Unbinder {
    private CityRegistrationOverviewFragment target;
    private View view2131755475;

    public CityRegistrationOverviewFragment_ViewBinding(final CityRegistrationOverviewFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5630R.C5632id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5630R.C5632id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C5630R.C5632id.save_button, "field 'saveButton' and method 'onApply'");
        target2.saveButton = (AirButton) Utils.castView(view, C5630R.C5632id.save_button, "field 'saveButton'", AirButton.class);
        this.view2131755475 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onApply();
            }
        });
    }

    public void unbind() {
        CityRegistrationOverviewFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.saveButton = null;
        this.view2131755475.setOnClickListener(null);
        this.view2131755475 = null;
    }
}
