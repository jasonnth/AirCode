package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RequirementChecklistRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.utils.AdditionalRequirementsHelper;
import com.airbnb.android.managelisting.C7368R;

public class ManageListingGuestRequirementsAdapter extends ManageListingAdapter {
    private int EMAIL_ADDRESS = C7368R.string.airbnb_requirements_book_list_email_address;
    private int HOUSE_RULES = C7368R.string.airbnb_requirements_book_list_house_rules;
    private int PAYMENT_INFO = C7368R.string.airbnb_requirements_book_list_payment_information;
    private int PHONE_NUMBER = C7368R.string.airbnb_requirements_book_list_phone_number;
    private int PROFILE_PHOTO = C7368R.string.airbnb_requirements_book_list_profile_photo;
    private int TRIP_PURPOSE = C7368R.string.airbnb_requirements_book_list_trip_purpose;
    private StandardRowEpoxyModel_ additionalRequirementsRowModel;
    private final Context context;

    public ManageListingGuestRequirementsAdapter(ManageListingDataController controller, Listing listing, Context context2) {
        super(controller);
        enableDiffing();
        this.controller = controller;
        this.context = context2;
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.guest_requirements_title).captionRes(C7368R.string.airbnb_requirements_subtitle));
        if (!FeatureToggles.showBetterFirstMessage()) {
            addModel(new ListSpacerEpoxyModel_().spaceHeight(48));
            addRequirementChecklistRow(this.EMAIL_ADDRESS);
            addRequirementChecklistRow(this.PHONE_NUMBER);
            addRequirementChecklistRow(this.PROFILE_PHOTO);
            addRequirementChecklistRow(this.PAYMENT_INFO);
            addRequirementChecklistRow(this.HOUSE_RULES);
            addRequirementChecklistRow(this.TRIP_PURPOSE);
            addModel(new ListSpacerEpoxyModel_().spaceHeight(48));
            addModel(new SubsectionDividerEpoxyModel_());
            createAdditionalRequirementsRow(listing, context2);
            return;
        }
        addModel(new StandardRowEpoxyModel_().titleRes(C7368R.string.airbnb_requirements_title).subtitle((CharSequence) context2.getString(this.EMAIL_ADDRESS) + ", " + context2.getString(this.PHONE_NUMBER) + ", " + context2.getString(this.PROFILE_PHOTO) + ", " + context2.getString(this.PAYMENT_INFO) + ", " + context2.getString(this.HOUSE_RULES) + ", " + context2.getString(this.TRIP_PURPOSE)).subTitleMaxLine(2));
        createAdditionalRequirementsRow(listing, context2);
        addModel(new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_required_trip_information_title).subtitle(C7368R.string.f10087xea6673e0).clickListener(ManageListingGuestRequirementsAdapter$$Lambda$1.lambdaFactory$(controller)).actionText(TextUtils.isEmpty(listing.getInstantBookWelcomeMessage()) ? C7368R.string.add : C7368R.string.edit));
    }

    private void addRequirementChecklistRow(int stringRes) {
        addModel(new RequirementChecklistRowEpoxyModel_().titleRes(stringRes).rowDrawableRes(C7213R.C7214drawable.n2_ic_check_babu));
    }

    private void createAdditionalRequirementsRow(Listing listing, Context context2) {
        this.additionalRequirementsRowModel = new StandardRowEpoxyModel_().titleRes(C7368R.string.airbnb_requirements_additional_requirements_title).actionText(C7368R.string.add).clickListener(ManageListingGuestRequirementsAdapter$$Lambda$2.lambdaFactory$(this));
        updateAdditionalRequirementsRow();
        addModel(this.additionalRequirementsRowModel);
    }

    public void setEnabled(boolean enabled) {
        this.additionalRequirementsRowModel.enabled(enabled);
        notifyModelsChanged();
    }

    private void updateAdditionalRequirementsRow() {
        AdditionalRequirementsHelper.updateAdditionalRequirementsRow(this.controller.getListing(), this.context, this.additionalRequirementsRowModel);
    }

    public void dataUpdated() {
        updateAdditionalRequirementsRow();
    }

    public void dataLoading(boolean loading) {
    }
}
