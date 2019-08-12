package com.airbnb.android.core.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.PrimaryButton;

public class DLSHouseRulesFragment_ViewBinding implements Unbinder {
    private DLSHouseRulesFragment target;
    private View view2131755199;

    public DLSHouseRulesFragment_ViewBinding(final DLSHouseRulesFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C0716R.C0718id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        View view = Utils.findRequiredView(source, C0716R.C0718id.button, "field 'primaryButton' and method 'confirmReadingHouseRules'");
        target2.primaryButton = (PrimaryButton) Utils.castView(view, C0716R.C0718id.button, "field 'primaryButton'", PrimaryButton.class);
        this.view2131755199 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.confirmReadingHouseRules();
            }
        });
    }

    public void unbind() {
        DLSHouseRulesFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.primaryButton = null;
        this.view2131755199.setOnClickListener(null);
        this.view2131755199 = null;
    }
}
