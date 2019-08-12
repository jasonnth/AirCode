package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.view.View;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.analytics.UnlistAnalytics;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.List;

public class UnlistingReasonsAdapter extends AirEpoxyAdapter {
    UnlistingReasonsAdapter(Context context, ManageListingDataController controller) {
        DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.manage_listing_unlist_reasons_title);
        StandardRowEpoxyModel_ snoozeRow = new StandardRowEpoxyModel_().titleRes(C7368R.string.manage_listing_unlist_reason_snooze).titleMaxLine(3).disclosure().clickListener(UnlistingReasonsAdapter$$Lambda$1.lambdaFactory$(controller));
        List<StandardRowEpoxyModel_> reasonRows = FluentIterable.from((E[]) UnlistReasonUtils.allReasons()).transform(UnlistingReasonsAdapter$$Lambda$2.lambdaFactory$(controller)).toList();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{header, snoozeRow});
        addModels((Collection<? extends EpoxyModel<?>>) reasonRows);
    }

    static /* synthetic */ void lambda$new$0(ManageListingDataController controller, View v) {
        UnlistAnalytics.trackSelectUnlistReason(controller.getListing(), "unlist_temporarily");
        controller.actionExecutor.snoozeListing(1);
    }

    static /* synthetic */ void lambda$null$1(ManageListingDataController controller, Integer reason, View v) {
        UnlistAnalytics.trackSelectUnlistReason(controller.getListing(), UnlistAnalytics.getUnlistReasonEventString(reason.intValue()));
        controller.actionExecutor.unlistReason(reason.intValue());
    }
}
