package com.airbnb.android.cohosting.epoxycontrollers;

import android.content.Context;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.fragments.CohostReasonSelectionFragment.Listener;
import com.airbnb.android.cohosting.shared.CohostReasonSelectionType;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.ArrayList;
import java.util.Iterator;

public class CohostReasonSelectionEpoxyController extends AirEpoxyController {
    private final Context context;
    private final Listener listener;
    DocumentMarqueeEpoxyModel_ marquee;
    private final String removedUserName;
    private final ArrayList<CohostReasonSelectionType> selectionTypes;

    public CohostReasonSelectionEpoxyController(Context context2, String removedUserName2, ArrayList selectionTypes2, Listener listener2) {
        this.context = context2;
        this.removedUserName = removedUserName2;
        this.selectionTypes = selectionTypes2;
        this.listener = listener2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marquee.titleRes(C5658R.string.cohosting_reason_selection_title).captionRes(C5658R.string.cohosting_reason_selection_explanation).addTo(this);
        addSelectionTypeEpoxyModels();
    }

    private void addSelectionTypeEpoxyModels() {
        Iterator it = this.selectionTypes.iterator();
        while (it.hasNext()) {
            CohostReasonSelectionType selectionType = (CohostReasonSelectionType) it.next();
            new StandardRowEpoxyModel_().titleMaxLine(2).m5602id((long) selectionType.getReasonKey()).title((CharSequence) this.context.getString(selectionType.getSelectionScreenReasonText(), new Object[]{this.removedUserName})).clickListener(CohostReasonSelectionEpoxyController$$Lambda$1.lambdaFactory$(this, selectionType)).addTo(this);
        }
    }
}
