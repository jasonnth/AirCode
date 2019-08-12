package com.airbnb.android.listyourspacedls.adapters;

import android.os.Bundle;
import com.airbnb.android.core.enums.ListYourSpacePricingMode;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.RadioRowModelManager;
import com.airbnb.android.core.utils.RadioRowModelManager.Listener;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.listing.adapters.InputAdapter;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.epoxy.EpoxyModel;
import icepick.State;

public class LYSSelectPricingTypeAdapter extends AirEpoxyAdapter implements InputAdapter {
    /* access modifiers changed from: private */
    public final SelectPricingTypeListener listener;
    private final long listingId;
    @State
    ListYourSpacePricingMode pricingMode;
    private final Listener<ListYourSpacePricingMode> pricingTypeRadioRowListener = new Listener<ListYourSpacePricingMode>() {
        public void onValueSelected(ListYourSpacePricingMode newMode) {
            LYSSelectPricingTypeAdapter.this.pricingMode = newMode;
            LYSSelectPricingTypeAdapter.this.listener.logChoosePricingMode(LYSSelectPricingTypeAdapter.this.isSmartPricingSelected());
            LYSSelectPricingTypeAdapter.this.checkValidity();
        }

        public void onModelUpdated(ToggleActionRowEpoxyModel_ model) {
            LYSSelectPricingTypeAdapter.this.notifyModelChanged(model);
        }
    };
    private final RadioRowModelManager<ListYourSpacePricingMode> radioRowManager = new RadioRowModelManager<>(this.pricingTypeRadioRowListener);

    public interface SelectPricingTypeListener {
        void isValid(boolean z);

        void logChoosePricingMode(boolean z);
    }

    public LYSSelectPricingTypeAdapter(Listing listing, SelectPricingTypeListener listener2, Bundle savedInstanceState) {
        super(true);
        enableDiffing();
        if (savedInstanceState == null) {
            this.pricingMode = listing.getListYourSpacePricingMode();
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        this.listingId = listing.getId();
        this.listener = listener2;
        DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_().titleRes(C7251R.string.lys_dls_select_pricing_type_title);
        createRadioRows();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{header});
        addModels(this.radioRowManager.getModels());
    }

    private void createRadioRows() {
        ToggleActionRowEpoxyModel_ smartPricingRow = new ToggleActionRowEpoxyModel_().titleRes(C7251R.string.lys_dls_smart_pricing_title).subtitleRes(C7251R.string.lys_dls_smart_pricing_explanation).labelRes(C7251R.string.recommended);
        ToggleActionRowEpoxyModel_ fixedPricingRow = new ToggleActionRowEpoxyModel_().titleRes(C7251R.string.lys_dls_fixed_price_title).subtitleRes(C7251R.string.lys_dls_fixed_price_explanation);
        this.radioRowManager.addRow(smartPricingRow, ListYourSpacePricingMode.Smart);
        this.radioRowManager.addRow(fixedPricingRow, ListYourSpacePricingMode.Fixed);
        if (this.pricingMode != ListYourSpacePricingMode.Undefined) {
            this.radioRowManager.setSelectedValue(this.pricingMode);
        }
    }

    public void checkValidity() {
        this.listener.isValid(this.pricingMode != ListYourSpacePricingMode.Undefined);
    }

    public boolean isSmartPricingSelected() {
        return this.pricingMode == ListYourSpacePricingMode.Smart;
    }

    public ListYourSpacePricingMode getPricingMode() {
        return this.pricingMode;
    }

    public DynamicPricingControl getNewDynamicPricingControl(DynamicPricingControl settings) {
        DynamicPricingControl newSettings = new DynamicPricingControl();
        newSettings.setIsEnabled(isSmartPricingSelected());
        newSettings.setMaxPrice(settings.getMaxPrice());
        newSettings.setMinPrice(settings.getMinPrice());
        newSettings.setDesiredHostingFrequencyKey(settings.getDesiredHostingFrequencyKey());
        newSettings.setListingId(this.listingId);
        return newSettings;
    }

    public void setInputEnabled(boolean enabled) {
        this.radioRowManager.setRowsEnabled(enabled);
        notifyModelsChanged();
    }
}
