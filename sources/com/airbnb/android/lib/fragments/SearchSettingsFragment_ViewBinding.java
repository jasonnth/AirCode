package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SwitchRow;

public class SearchSettingsFragment_ViewBinding implements Unbinder {
    private SearchSettingsFragment target;

    public SearchSettingsFragment_ViewBinding(SearchSettingsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.image, "field 'toolbar'", AirToolbar.class);
        target2.showTotalPriceSetting = (SwitchRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.show_total_price_setting, "field 'showTotalPriceSetting'", SwitchRow.class);
    }

    public void unbind() {
        SearchSettingsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.showTotalPriceSetting = null;
    }
}
