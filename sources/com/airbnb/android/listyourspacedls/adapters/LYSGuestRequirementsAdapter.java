package com.airbnb.android.listyourspacedls.adapters;

import android.content.Context;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RequirementChecklistRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.utils.AdditionalRequirementsHelper;
import com.airbnb.android.listyourspacedls.C7251R;

public class LYSGuestRequirementsAdapter extends AirEpoxyAdapter {
    private StandardRowEpoxyModel_ additionalRequirementsRowModel;
    private final Controller controller;

    public interface Controller {
        void addAdditionalRequirements();
    }

    public LYSGuestRequirementsAdapter(Controller controller2, Listing listing, Context context) {
        super(true);
        enableDiffing();
        this.controller = controller2;
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(C7213R.string.lys_airbnb_requirements_title).captionRes(C7213R.string.lys_airbnb_requirements_subtitle));
        addModel(new ListSpacerEpoxyModel_().spaceHeight(48));
        addRequirementChecklistRow(C7251R.string.lys_airbnb_requirements_book_list_email_address);
        addRequirementChecklistRow(C7251R.string.lys_airbnb_requirements_book_list_phone_number);
        addRequirementChecklistRow(C7251R.string.lys_airbnb_requirements_book_list_profile_photo);
        addRequirementChecklistRow(C7251R.string.lys_airbnb_requirements_book_list_payment_information);
        addRequirementChecklistRow(C7251R.string.lys_airbnb_requirements_book_list_house_rules);
        addRequirementChecklistRow(C7251R.string.lys_airbnb_requirements_book_list_trip_purpose);
        addModel(new ListSpacerEpoxyModel_().spaceHeight(48));
        addModel(new SubsectionDividerEpoxyModel_());
        createAdditionalRequirementsRow(listing, context);
    }

    private void addRequirementChecklistRow(int stringRes) {
        addModel(new RequirementChecklistRowEpoxyModel_().titleRes(stringRes).rowDrawableRes(C7213R.C7214drawable.n2_ic_check_babu));
    }

    private void createAdditionalRequirementsRow(Listing listing, Context context) {
        this.additionalRequirementsRowModel = new StandardRowEpoxyModel_().titleRes(C7213R.string.lys_airbnb_requirements_additional_requirements_title).actionText(C7213R.string.add);
        requirementsUpdated(listing, context);
        this.additionalRequirementsRowModel.clickListener(LYSGuestRequirementsAdapter$$Lambda$1.lambdaFactory$(this));
        addModel(this.additionalRequirementsRowModel);
    }

    public void requirementsUpdated(Listing listing, Context context) {
        AdditionalRequirementsHelper.updateAdditionalRequirementsRow(listing, context, this.additionalRequirementsRowModel);
    }

    public void setEnabled(boolean enabled) {
        this.additionalRequirementsRowModel.enabled(enabled);
        notifyModelsChanged();
    }
}
