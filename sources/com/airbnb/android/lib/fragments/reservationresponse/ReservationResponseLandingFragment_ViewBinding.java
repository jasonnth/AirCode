package com.airbnb.android.lib.fragments.reservationresponse;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BottomButtonBar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.TextRow;

public class ReservationResponseLandingFragment_ViewBinding implements Unbinder {
    private ReservationResponseLandingFragment target;

    public ReservationResponseLandingFragment_ViewBinding(ReservationResponseLandingFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.document_marquee, "field 'documentMarquee'", DocumentMarquee.class);
        target2.reflectionMessageView = (TextRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.ro_response_reflection_message, "field 'reflectionMessageView'", TextRow.class);
        target2.buttonBar = (BottomButtonBar) Utils.findRequiredViewAsType(source, C0880R.C0882id.button_bar, "field 'buttonBar'", BottomButtonBar.class);
    }

    public void unbind() {
        ReservationResponseLandingFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.documentMarquee = null;
        target2.reflectionMessageView = null;
        target2.buttonBar = null;
    }
}
