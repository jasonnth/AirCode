package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SimpleTextRow;

public class KonaHouseManualFragment_ViewBinding implements Unbinder {
    private KonaHouseManualFragment target;

    public KonaHouseManualFragment_ViewBinding(KonaHouseManualFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.houseManualTextRow = (SimpleTextRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.house_manual, "field 'houseManualTextRow'", SimpleTextRow.class);
    }

    public void unbind() {
        KonaHouseManualFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.houseManualTextRow = null;
    }
}
