package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingCheckInTimeOptions;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.listing.utils.CheckInOutSettingsHelper;
import com.airbnb.android.listing.utils.CheckInOutSettingsHelper.Listener;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.Strap;
import com.airbnb.epoxy.EpoxyModel;
import java.util.List;

public class ManageListingCheckInOutAdapter extends AirEpoxyAdapter {
    private final CheckInOutSettingsHelper helper;
    private final Listener helperListener = ManageListingCheckInOutAdapter$$Lambda$1.lambdaFactory$(this);

    public ManageListingCheckInOutAdapter(Context context, Listing listing, ListingCheckInTimeOptions checkInTimeOptions, Bundle savedInstanceState, List<CheckInInformation> list) {
        super(true);
        enableDiffing();
        this.helper = new CheckInOutSettingsHelper(context, listing, this.helperListener, checkInTimeOptions, savedInstanceState);
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.manage_listing_check_in_out_title));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new SectionHeaderEpoxyModel_().titleRes(C7368R.string.manage_listing_check_in_out_section_check_in_title), this.helper.getCheckInStartTimeInput(), this.helper.getCheckInEndTimeInput()});
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new SectionHeaderEpoxyModel_().titleRes(C7368R.string.manage_listing_check_in_out_section_check_out_title), this.helper.getCheckOutTimeInput()});
    }

    public void setEnabled(boolean enabled) {
        this.helper.setEnabled(enabled);
        notifyModelsChanged();
    }

    public Strap getCheckInSettings() {
        return this.helper.getCheckInSettings();
    }

    public boolean hasChanged(Listing listing) {
        return this.helper.hasChanged(listing);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.helper.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        this.helper.onRestoreInstanceState(inState);
    }
}
