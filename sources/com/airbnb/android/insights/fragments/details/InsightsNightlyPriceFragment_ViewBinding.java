package com.airbnb.android.insights.fragments.details;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.insights.C6552R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class InsightsNightlyPriceFragment_ViewBinding implements Unbinder {
    private InsightsNightlyPriceFragment target;
    private View view2131755544;

    public InsightsNightlyPriceFragment_ViewBinding(final InsightsNightlyPriceFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C6552R.C6554id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6552R.C6554id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C6552R.C6554id.save_button, "field 'saveButton' and method 'onSave'");
        target2.saveButton = (AirButton) Utils.castView(view, C6552R.C6554id.save_button, "field 'saveButton'", AirButton.class);
        this.view2131755544 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSave();
            }
        });
    }

    public void unbind() {
        InsightsNightlyPriceFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
        target2.saveButton = null;
        this.view2131755544.setOnClickListener(null);
        this.view2131755544 = null;
    }
}
