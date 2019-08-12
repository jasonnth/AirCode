package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.utils.AdditionalRequirementsHelper;
import com.airbnb.p027n2.components.SwitchStyle;
import icepick.State;

public class GuestAdditionalRequirementsAdapter extends AirEpoxyAdapter implements InputAdapter {
    /* access modifiers changed from: 0000 */
    @State
    public boolean governmentIdChecked;
    private SwitchRowEpoxyModel_ governmentIdSwitch = new SwitchRowEpoxyModel_().titleRes(C7213R.string.lys_additional_requirements_government_id).style(SwitchStyle.Filled).showDivider(true).checkedChangeListener(GuestAdditionalRequirementsAdapter$$Lambda$1.lambdaFactory$(this));
    /* access modifiers changed from: 0000 */
    @State
    public boolean hostRecommendationChecked;
    private SwitchRowEpoxyModel_ hostRecommendationSwitch;

    public GuestAdditionalRequirementsAdapter(Listing listing, Bundle savedInstanceState) {
        super(true);
        enableDiffing();
        InstantBookingAllowedCategory category = AdditionalRequirementsHelper.getInstantBookingAllowedCategory(listing);
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(C7213R.string.lys_additional_requirements_title));
        addModel(this.governmentIdSwitch);
        this.hostRecommendationSwitch = new SwitchRowEpoxyModel_().titleRes(C7213R.string.lys_additional_requirements_host_recommendation).style(SwitchStyle.Filled).showDivider(true).checkedChangeListener(GuestAdditionalRequirementsAdapter$$Lambda$2.lambdaFactory$(this));
        addModel(this.hostRecommendationSwitch);
        addModel(new SubsectionDividerEpoxyModel_());
        this.governmentIdSwitch.checked(category.isGovernmentIdNeeded());
        this.hostRecommendationSwitch.checked(category.isHighRatingNeeded());
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    private void updateModels() {
        this.governmentIdSwitch.checked(this.governmentIdChecked);
        this.hostRecommendationSwitch.checked(this.hostRecommendationChecked);
        notifyModelsChanged();
    }

    public InstantBookingAllowedCategory getInstantBookingAllowedCategory() {
        return InstantBookingAllowedCategory.fromListingState(true, this.governmentIdSwitch.checked(), this.hostRecommendationSwitch.checked());
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            updateModels();
        }
    }

    public void setInputEnabled(boolean enabled) {
        this.governmentIdSwitch.enabled(enabled);
        this.hostRecommendationSwitch.enabled(enabled);
        notifyModelsChanged();
    }

    public boolean hasChanged(Listing listing) {
        return AdditionalRequirementsHelper.getInstantBookingAllowedCategory(listing) != getInstantBookingAllowedCategory();
    }
}
