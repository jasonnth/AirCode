package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.components.StandardRow;

public class RetractRequestFragment_ViewBinding implements Unbinder {
    private RetractRequestFragment target;
    private View view2131755232;

    public RetractRequestFragment_ViewBinding(final RetractRequestFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.listingRow = (SimpleTextRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.listing_name, "field 'listingRow'", SimpleTextRow.class);
        target2.dateRow = (StandardRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.date, "field 'dateRow'", StandardRow.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.button, "method 'cancelRequest'");
        this.view2131755232 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.cancelRequest();
            }
        });
    }

    public void unbind() {
        RetractRequestFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.listingRow = null;
        target2.dateRow = null;
        this.view2131755232.setOnClickListener(null);
        this.view2131755232 = null;
    }
}
