package com.airbnb.android.cohosting.epoxycontrollers;

import android.content.Context;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class CohostLeadsCenterChoosePackageEpoxyController extends AirEpoxyController {
    private final Context context;
    private final String firstName;
    ToggleActionRowEpoxyModel_ fullServiceModel;
    LinkActionRowEpoxyModel_ linkModel;
    DocumentMarqueeEpoxyModel_ marqueeModel;
    ToggleActionRowEpoxyModel_ onlineModel;
    ToggleActionRowEpoxyModel_ onsiteModel;

    public CohostLeadsCenterChoosePackageEpoxyController(Context context2, String firstName2) {
        this.context = context2;
        this.firstName = firstName2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marqueeModel.titleRes(C5658R.string.cohosting_leads_center_choose_page_title).captionText((CharSequence) this.context.getString(C5658R.string.cohosting_leads_center_choose_page_subtitle, new Object[]{this.firstName}));
        this.fullServiceModel.titleRes(C5658R.string.cohosting_leads_center_package_type_full_service);
        this.onlineModel.titleRes(C5658R.string.cohosting_leads_center_package_type_online_service);
        this.onsiteModel.titleRes(C5658R.string.cohosting_leads_center_package_type_onsite_service);
        this.linkModel.textRes(C5658R.string.cohosting_leads_center_page_details_link);
    }
}
