package com.airbnb.android.lib.businesstravel;

import com.airbnb.android.core.businesstravel.BusinessTravelUtils;
import com.airbnb.android.core.enums.WorkEmailStatus;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class WorkEmailEpoxyController extends AirEpoxyController {
    DocumentMarqueeEpoxyModel_ documentMarquee;
    /* access modifiers changed from: private */
    public String email;
    InlineInputRowEpoxyModel_ inlineInputRow;
    private WorkEmailStatus workEmailStatus;

    public WorkEmailEpoxyController(String email2, WorkEmailStatus workEmailStatus2) {
        this.email = email2;
        this.workEmailStatus = workEmailStatus2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        switch (this.workEmailStatus) {
            case None:
                this.documentMarquee.titleRes(C0880R.string.bt_work_email_title).captionRes(BusinessTravelUtils.shouldShowAddWorkEmailPromoText() ? C0880R.string.bt_work_email_promo_subtitle : C0880R.string.bt_work_email_subtitle);
                this.inlineInputRow.titleRes(C0880R.string.bt_add_work_email).inputChangedListener(WorkEmailEpoxyController$$Lambda$1.lambdaFactory$(this));
                return;
            case Pending:
            case Verified:
                this.documentMarquee.titleRes(C0880R.string.bt_edit_work_email_title);
                this.inlineInputRow.titleRes(C0880R.string.bt_edit_work_email).input(this.email).inputChangedListener(WorkEmailEpoxyController$$Lambda$2.lambdaFactory$(this));
                return;
            default:
                return;
        }
    }

    public String getEmail() {
        return this.email;
    }
}
