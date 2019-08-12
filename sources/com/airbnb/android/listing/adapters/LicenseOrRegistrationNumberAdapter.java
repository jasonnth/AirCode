package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Strings;
import icepick.State;

public class LicenseOrRegistrationNumberAdapter extends AirEpoxyAdapter implements InputAdapter {
    @State
    String license;
    private final InlineInputRowEpoxyModel_ licenseOrRegistrationNumber;

    public LicenseOrRegistrationNumberAdapter(String license2, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.license = license2;
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        DocumentMarqueeEpoxyModel_ documentMarquee = new DocumentMarqueeEpoxyModel_().titleRes(C7213R.string.manage_listing_booking_item_license_or_registration_number).captionRes(C7213R.string.license_or_registration_number_description);
        this.licenseOrRegistrationNumber = new InlineInputRowEpoxyModel_().titleRes(C7213R.string.license_or_registration_number).input(Strings.nullToEmpty(this.license));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{documentMarquee, this.licenseOrRegistrationNumber});
    }

    public void onSaveInstanceState(Bundle outState) {
        this.license = this.licenseOrRegistrationNumber.input().toString();
        super.onSaveInstanceState(outState);
    }

    public String getLicense() {
        return this.licenseOrRegistrationNumber.input().toString();
    }

    public void setInputEnabled(boolean enabled) {
        this.licenseOrRegistrationNumber.enabled(enabled);
        notifyModelChanged(this.licenseOrRegistrationNumber);
    }

    public boolean hasChanged(Listing listing) {
        return !getLicense().equals(SanitizeUtils.emptyIfNull(listing.getLicense()));
    }
}
