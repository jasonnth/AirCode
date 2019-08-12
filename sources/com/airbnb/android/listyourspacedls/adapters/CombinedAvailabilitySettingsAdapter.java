package com.airbnb.android.listyourspacedls.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingCheckInTimeOptions;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.listing.adapters.InputAdapter;
import com.airbnb.android.listing.adapters.TripLengthSettingsHelper;
import com.airbnb.android.listing.utils.AvailabilitySettingsHelper;
import com.airbnb.android.listing.utils.AvailabilitySettingsHelper.Listener;
import com.airbnb.android.listing.utils.CheckInOutSettingsHelper;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.utils.Strap;
import com.airbnb.epoxy.EpoxyModel;
import org.json.JSONObject;

public class CombinedAvailabilitySettingsAdapter extends AirEpoxyAdapter implements InputAdapter {
    private final AvailabilitySettingsHelper availabilitySettingsHelper;
    private final CheckInOutSettingsHelper checkInOutSettingsHelper;
    private final TripLengthSettingsHelper tripLengthSettingsHelper;

    public CombinedAvailabilitySettingsAdapter(Context context, CalendarRule calendarRule, Listing listing, boolean isInstantBookEnabled, ListingCheckInTimeOptions checkInTimeOptions, Bundle savedInstanceState) {
        super(true);
        enableDiffing();
        Listener availabilitySettingsHelperListener = CombinedAvailabilitySettingsAdapter$$Lambda$1.lambdaFactory$(this);
        CheckInOutSettingsHelper.Listener checkInOutSettingsHelperListener = CombinedAvailabilitySettingsAdapter$$Lambda$2.lambdaFactory$(this);
        TripLengthSettingsHelper.Listener tripLengthSettingsHelperListener = CombinedAvailabilitySettingsAdapter$$Lambda$3.lambdaFactory$(this);
        this.checkInOutSettingsHelper = new CheckInOutSettingsHelper(context, listing, checkInOutSettingsHelperListener, checkInTimeOptions, savedInstanceState);
        this.tripLengthSettingsHelper = new TripLengthSettingsHelper(listing, calendarRule, tripLengthSettingsHelperListener, savedInstanceState);
        this.availabilitySettingsHelper = new AvailabilitySettingsHelper(context, calendarRule, isInstantBookEnabled, availabilitySettingsHelperListener, savedInstanceState);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new DocumentMarqueeEpoxyModel_().titleRes(C7251R.string.lys_dls_availability_title), new SectionHeaderEpoxyModel_().titleRes(C7251R.string.lys_dls_availability_booking_window), this.availabilitySettingsHelper.getAdvanceNoticeRow(), this.availabilitySettingsHelper.getRequestToBookRow(), this.availabilitySettingsHelper.getCutoffTimeRow(), this.availabilitySettingsHelper.getFutureReservationsRow(), new SectionHeaderEpoxyModel_().titleRes(C7251R.string.lys_dls_availability_check_in), this.checkInOutSettingsHelper.getCheckInStartTimeInput(), this.checkInOutSettingsHelper.getCheckInEndTimeInput(), this.checkInOutSettingsHelper.getCheckOutTimeInput(), new SectionHeaderEpoxyModel_().titleRes(C7251R.string.manage_listing_trip_length_title), this.tripLengthSettingsHelper.getMinNightsInputRow(), this.tripLengthSettingsHelper.getMaxNightsInputRow(), this.tripLengthSettingsHelper.getWeekendMinNightsInputRow()});
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.checkInOutSettingsHelper.onSaveInstanceState(outState);
        this.tripLengthSettingsHelper.onSaveInstanceState(outState);
        this.availabilitySettingsHelper.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        this.checkInOutSettingsHelper.onRestoreInstanceState(inState);
        this.tripLengthSettingsHelper.onRestoreInstanceState(inState);
        this.availabilitySettingsHelper.onRestoreInstanceState(inState);
    }

    public void setInputEnabled(boolean enabled) {
        this.checkInOutSettingsHelper.setEnabled(enabled);
        this.tripLengthSettingsHelper.setEnabled(enabled);
        this.availabilitySettingsHelper.setInputEnabled(enabled);
        notifyModelsChanged();
    }

    public boolean notifyValidSettingsListener() {
        boolean isValid = this.tripLengthSettingsHelper.validateInputsAndShowError();
        notifyModelsChanged();
        return isValid;
    }

    public Strap getUpdateListingSettings() {
        return this.checkInOutSettingsHelper.getCheckInSettings().mix(this.tripLengthSettingsHelper.getUpdateListingSettings());
    }

    public JSONObject getCalendarRulesUpdateSettings() {
        JSONObject requestHash = new JSONObject();
        this.availabilitySettingsHelper.getCalendarRulesSettings(requestHash);
        this.tripLengthSettingsHelper.getCalendarRulesUpdateSettings(requestHash);
        return requestHash;
    }

    public boolean hasChanged(Listing listing, CalendarRule calendarRule) {
        return this.availabilitySettingsHelper.hasChanged(calendarRule) || this.checkInOutSettingsHelper.hasChanged(listing) || this.tripLengthSettingsHelper.hasChanged(listing, calendarRule);
    }

    public boolean hasAvailabilitySettingsChanged(CalendarRule calendarRule) {
        return this.availabilitySettingsHelper.hasChanged(calendarRule);
    }
}
