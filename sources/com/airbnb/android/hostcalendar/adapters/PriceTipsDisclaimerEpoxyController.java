package com.airbnb.android.hostcalendar.adapters;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class PriceTipsDisclaimerEpoxyController extends AirEpoxyController {
    MicroSectionHeaderEpoxyModel_ availabilityModel;
    DocumentMarqueeEpoxyModel_ headerModel;
    SimpleTextRowEpoxyModel_ legalDisclaimerModel;
    MicroSectionHeaderEpoxyModel_ qualityModel;
    MicroSectionHeaderEpoxyModel_ searchesModel;
    MicroSectionHeaderEpoxyModel_ timeLeftModel;

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.headerModel.titleRes(C6418R.string.multi_day_price_tips_disclaimer_title).addTo(this);
        this.searchesModel.titleRes(C6418R.string.multi_day_price_tips_disclaimer_searches).descriptionRes(C6418R.string.multi_day_price_tips_disclaimer_searches_description).addTo(this);
        this.availabilityModel.titleRes(C6418R.string.multi_day_price_tips_disclaimer_availability).descriptionRes(C6418R.string.multi_day_price_tips_disclaimer_availability_description).addTo(this);
        this.timeLeftModel.titleRes(C6418R.string.multi_day_price_tips_disclaimer_time_left).descriptionRes(C6418R.string.multi_day_price_tips_disclaimer_time_left_description).addTo(this);
        this.qualityModel.titleRes(C6418R.string.multi_day_price_tips_disclaimer_quality).descriptionRes(C6418R.string.multi_day_price_tips_disclaimer_quality_description).addTo(this);
        this.legalDisclaimerModel.textRes(C6418R.string.manage_listing_pricing_disclaimer_price_tips_info).smallAndMuted().addTo(this);
    }
}
