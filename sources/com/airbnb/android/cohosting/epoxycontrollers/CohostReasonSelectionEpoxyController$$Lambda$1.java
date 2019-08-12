package com.airbnb.android.cohosting.epoxycontrollers;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.cohosting.shared.CohostReasonSelectionType;

final /* synthetic */ class CohostReasonSelectionEpoxyController$$Lambda$1 implements OnClickListener {
    private final CohostReasonSelectionEpoxyController arg$1;
    private final CohostReasonSelectionType arg$2;

    private CohostReasonSelectionEpoxyController$$Lambda$1(CohostReasonSelectionEpoxyController cohostReasonSelectionEpoxyController, CohostReasonSelectionType cohostReasonSelectionType) {
        this.arg$1 = cohostReasonSelectionEpoxyController;
        this.arg$2 = cohostReasonSelectionType;
    }

    public static OnClickListener lambdaFactory$(CohostReasonSelectionEpoxyController cohostReasonSelectionEpoxyController, CohostReasonSelectionType cohostReasonSelectionType) {
        return new CohostReasonSelectionEpoxyController$$Lambda$1(cohostReasonSelectionEpoxyController, cohostReasonSelectionType);
    }

    public void onClick(View view) {
        this.arg$1.listener.onCohostReasonSelection(this.arg$2);
    }
}
