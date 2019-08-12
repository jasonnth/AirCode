package com.airbnb.android.listing.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.utils.AvailabilitySettingsHelper;
import com.airbnb.android.listing.utils.AvailabilitySettingsHelper.Listener;
import com.airbnb.epoxy.EpoxyModel;

public class AvailabilitySettingsAdapter extends AirEpoxyAdapter {
    private final AvailabilitySettingsHelper helper;
    private final Listener helperListener = AvailabilitySettingsAdapter$$Lambda$1.lambdaFactory$(this);

    public AvailabilitySettingsAdapter(Context context, CalendarRule calendarRule, Listing listing, Bundle savedInstanceState) {
        super(true);
        enableDiffing();
        this.helper = new AvailabilitySettingsHelper(context, calendarRule, listing.isInstantBookEnabled(), this.helperListener, savedInstanceState);
        DocumentMarqueeEpoxyModel_ titleRow = new DocumentMarqueeEpoxyModel_().titleRes(C7213R.string.manage_listing_booking_item_availability_rules);
        this.helper.updateRows();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{titleRow, this.helper.getAdvanceNoticeRow(), this.helper.getRequestToBookRow(), this.helper.getCutoffTimeRow(), this.helper.getPreparationTimeRow(), this.helper.getFutureReservationsRow()});
    }

    public void setInputEnabled(boolean enabled) {
        this.helper.setInputEnabled(enabled);
        notifyModelsChanged();
    }

    public CalendarRule getNewAvailabilitySettings() {
        return this.helper.getNewSettings();
    }

    public boolean hasChanged(CalendarRule calendarRule) {
        return this.helper.hasChanged(calendarRule);
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
