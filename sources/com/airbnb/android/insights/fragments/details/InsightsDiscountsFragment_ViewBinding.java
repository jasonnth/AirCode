package com.airbnb.android.insights.fragments.details;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.insights.C6552R;
import com.airbnb.p027n2.primitives.AirButton;

public class InsightsDiscountsFragment_ViewBinding implements Unbinder {
    private InsightsDiscountsFragment target;

    public InsightsDiscountsFragment_ViewBinding(InsightsDiscountsFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C6552R.C6554id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.saveButton = (AirButton) Utils.findRequiredViewAsType(source, C6552R.C6554id.save_button, "field 'saveButton'", AirButton.class);
    }

    public void unbind() {
        InsightsDiscountsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.saveButton = null;
    }
}
