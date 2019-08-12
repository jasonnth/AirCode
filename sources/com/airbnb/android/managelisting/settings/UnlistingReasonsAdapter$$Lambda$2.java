package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.google.common.base.Function;

final /* synthetic */ class UnlistingReasonsAdapter$$Lambda$2 implements Function {
    private final ManageListingDataController arg$1;

    private UnlistingReasonsAdapter$$Lambda$2(ManageListingDataController manageListingDataController) {
        this.arg$1 = manageListingDataController;
    }

    public static Function lambdaFactory$(ManageListingDataController manageListingDataController) {
        return new UnlistingReasonsAdapter$$Lambda$2(manageListingDataController);
    }

    public Object apply(Object obj) {
        return new StandardRowEpoxyModel_().titleRes(UnlistReasonUtils.getReasonStringRes(((Integer) obj).intValue())).titleMaxLine(3).disclosure().clickListener(UnlistingReasonsAdapter$$Lambda$3.lambdaFactory$(this.arg$1, (Integer) obj));
    }
}
