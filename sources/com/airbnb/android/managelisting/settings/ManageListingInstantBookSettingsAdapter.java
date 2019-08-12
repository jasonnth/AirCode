package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.RadioRowModelManager;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.SwitchStyle;
import icepick.State;

class ManageListingInstantBookSettingsAdapter extends AirEpoxyAdapter {
    private final RadioRowModelManager<Boolean> instantBookRadioManager;
    private final com.airbnb.android.core.utils.RadioRowModelManager.Listener<Boolean> instantBookRadioModelListener = new com.airbnb.android.core.utils.RadioRowModelManager.Listener<Boolean>() {
        public void onValueSelected(Boolean enabled) {
            if (!enabled.booleanValue()) {
                ManageListingInstantBookSettingsAdapter.this.listener.onDisableInstantBookSelected();
            }
        }

        public void onModelUpdated(ToggleActionRowEpoxyModel_ model) {
            ManageListingInstantBookSettingsAdapter.this.notifyModelChanged(model);
        }
    };
    @State
    InstantBookingAllowedCategory instantBookingCategory;
    /* access modifiers changed from: private */
    public final Listener listener;
    private final SwitchRowEpoxyModel_ requirementGovId;
    private final SwitchRowEpoxyModel_ requirementHostRecommended;

    interface Listener {
        void onAirbnbRequirementsSelected();

        void onDisableInstantBookSelected();
    }

    ManageListingInstantBookSettingsAdapter(Listing listing, Listener listener2, Bundle savedInstanceState) {
        boolean z = true;
        super(true);
        this.listener = listener2;
        onRestoreInstanceState(savedInstanceState);
        if (this.instantBookingCategory == null) {
            this.instantBookingCategory = InstantBookingAllowedCategory.fromKey(listing.getInstantBookingAllowedCategory());
        }
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.manage_listing_booking_item_instant_book));
        this.instantBookRadioManager = new RadioRowModelManager(this.instantBookRadioModelListener).addRow(C7368R.string.booking_settings_everyone, Boolean.valueOf(true)).addRow(C7368R.string.ml_ib_visibility_no_one, Boolean.valueOf(false));
        addModels(this.instantBookRadioManager.getModels());
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new LinkActionRowEpoxyModel_().textRes(C7368R.string.instant_book_what_are_airbnb_requirements).clickListener(ManageListingInstantBookSettingsAdapter$$Lambda$1.lambdaFactory$(listener2))});
        SectionHeaderEpoxyModel_ additionalRequirementsHeader = new SectionHeaderEpoxyModel_().titleRes(C7368R.string.ml_ib_additional_requirements);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{additionalRequirementsHeader});
        this.requirementGovId = new SwitchRowEpoxyModel_().titleRes(C7368R.string.booking_requirements_government_id).style(SwitchStyle.Filled).checked(this.instantBookingCategory.isGovernmentIdNeeded()).checkedChangeListener(ManageListingInstantBookSettingsAdapter$$Lambda$2.lambdaFactory$(this));
        this.requirementHostRecommended = new SwitchRowEpoxyModel_().titleRes(C7368R.string.instant_book_settings_recommended_by_hosts).style(SwitchStyle.Filled).checked(this.instantBookingCategory.isHighRatingNeeded()).checkedChangeListener(ManageListingInstantBookSettingsAdapter$$Lambda$3.lambdaFactory$(this));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.requirementGovId, this.requirementHostRecommended});
        if (FeatureToggles.showBetterFirstMessage()) {
            additionalRequirementsHeader.hide();
            this.requirementGovId.hide();
            this.requirementHostRecommended.hide();
        }
        RadioRowModelManager<Boolean> radioRowModelManager = this.instantBookRadioManager;
        if (this.instantBookingCategory == InstantBookingAllowedCategory.Off) {
            z = false;
        }
        radioRowModelManager.setSelectedValue(Boolean.valueOf(z));
    }

    public void onSaveInstanceState(Bundle outState) {
        this.instantBookingCategory = getCategory();
        super.onSaveInstanceState(outState);
    }

    public boolean hasChanged(Listing listing) {
        if (!FeatureToggles.showBetterFirstMessage() && getCategory() != InstantBookingAllowedCategory.fromKey(listing.getInstantBookingAllowedCategory())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public InstantBookingAllowedCategory getCategory() {
        return InstantBookingAllowedCategory.fromListingState(((Boolean) Check.notNull(this.instantBookRadioManager.getSelectedValue())).booleanValue(), this.requirementGovId.checked(), this.requirementHostRecommended.checked());
    }

    /* access modifiers changed from: 0000 */
    public void setInputEnabled(boolean enabled) {
        this.instantBookRadioManager.setRowsEnabled(enabled);
        notifyModelChanged(this.requirementGovId.enabled(enabled));
        notifyModelChanged(this.requirementHostRecommended.enabled(enabled));
    }

    /* access modifiers changed from: private */
    public void requirementChecked(boolean isChecked) {
        if (isChecked) {
            this.instantBookRadioManager.setSelectedValue(Boolean.valueOf(true));
        }
    }

    public void setInstantBookDisabled() {
        this.requirementGovId.checked(false);
        this.requirementHostRecommended.checked(false);
        notifyModelChanged(this.requirementGovId);
        notifyModelChanged(this.requirementHostRecommended);
    }

    public void setInstantBookEnabled() {
        this.instantBookRadioManager.setSelectedValue(Boolean.valueOf(true));
    }
}
