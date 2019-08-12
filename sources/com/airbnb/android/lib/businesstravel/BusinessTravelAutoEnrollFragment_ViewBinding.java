package com.airbnb.android.lib.businesstravel;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;

public class BusinessTravelAutoEnrollFragment_ViewBinding implements Unbinder {
    private BusinessTravelAutoEnrollFragment target;

    public BusinessTravelAutoEnrollFragment_ViewBinding(BusinessTravelAutoEnrollFragment target2, View source) {
        this.target = target2;
        target2.sheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.sheet_marquee, "field 'sheetMarquee'", SheetMarquee.class);
        target2.gotItButton = (AirButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.got_it_button, "field 'gotItButton'", AirButton.class);
        target2.legalDisclaimerRow = (SimpleTextRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.legal_disclaimer, "field 'legalDisclaimerRow'", SimpleTextRow.class);
    }

    public void unbind() {
        BusinessTravelAutoEnrollFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sheetMarquee = null;
        target2.gotItButton = null;
        target2.legalDisclaimerRow = null;
    }
}
