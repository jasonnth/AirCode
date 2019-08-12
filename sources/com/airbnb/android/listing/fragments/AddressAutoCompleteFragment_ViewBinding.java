package com.airbnb.android.listing.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.listing.C7213R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InlineInputRow;

public class AddressAutoCompleteFragment_ViewBinding implements Unbinder {
    private AddressAutoCompleteFragment target;

    public AddressAutoCompleteFragment_ViewBinding(AddressAutoCompleteFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7213R.C7215id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7213R.C7215id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.addressInput = (InlineInputRow) Utils.findRequiredViewAsType(source, C7213R.C7215id.input_row_address, "field 'addressInput'", InlineInputRow.class);
        target2.loadingOverlay = Utils.findRequiredView(source, C7213R.C7215id.loading_overlay, "field 'loadingOverlay'");
    }

    public void unbind() {
        AddressAutoCompleteFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
        target2.addressInput = null;
        target2.loadingOverlay = null;
    }
}
