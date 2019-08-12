package com.airbnb.android.cohosting.epoxycontrollers;

import android.content.Context;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.controllers.CohostLeadsCenterDataController;
import com.airbnb.android.core.models.CohostInquiry;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.p027n2.components.MultiLineSplitRowModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class CohostLeadsCenterWhatYouCanEarnEpoxyController extends AirEpoxyController {
    private final Context context;
    private final CohostLeadsCenterDataController controller;
    SimpleTextRowEpoxyModel_ disclaimerModel;
    SectionHeaderEpoxyModel_ earningSummaryModel;
    MultiLineSplitRowModel_ hostingFeeModel;
    private final CohostInquiry inquiry;
    private final long inquiryId;
    MultiLineSplitRowModel_ lengthOfHostingModel;
    MultiLineSplitRowModel_ locationModel;
    DocumentMarqueeEpoxyModel_ marqueeModel;
    MultiLineSplitRowModel_ roomCapacityModel;
    MultiLineSplitRowModel_ roomTypeModel;

    public CohostLeadsCenterWhatYouCanEarnEpoxyController(Context context2, CohostLeadsCenterDataController controller2, long inquiryId2) {
        this.context = context2;
        this.controller = controller2;
        this.inquiryId = inquiryId2;
        this.inquiry = controller2.getCohostInquiry(inquiryId2);
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marqueeModel.titleRes(C5658R.string.cohost_leads_center_earning_title);
        this.earningSummaryModel.title(this.context.getString(C5658R.string.cohost_leads_center_earning_sum, new Object[]{this.controller.getEarningSumWithCurrency(this.inquiryId)})).descriptionRes(C5658R.string.cohost_leads_center_earning_explanation);
        this.roomTypeModel.title(C5658R.string.cohost_leads_center_room_type).infoText(this.inquiry.getRoomType());
        this.roomCapacityModel.title(C5658R.string.cohost_leads_center_room_capacity).withNoTopPaddingLayout().infoText(this.inquiry.getPersonCapacity());
        this.locationModel.title(C5658R.string.cohost_leads_center_location).withNoTopPaddingLayout().infoText(this.inquiry.getPartialAddressNative());
        this.lengthOfHostingModel.title(C5658R.string.cohost_leads_center_length_of_hosting).withNoTopPaddingLayout().infoText(this.controller.getLengthOfHosting(this.inquiryId));
        this.hostingFeeModel.title(C5658R.string.cohost_leads_center_hosting_fee).infoText(this.controller.getHostingFee(this.inquiryId)).withNoTopPaddingLayout().showDivider(true);
        this.disclaimerModel.textRes(C5658R.string.cohost_leads_center_earning_disclaimer).smallAndMuted();
    }
}
