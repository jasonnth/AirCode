package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.CalendarRulesRequest;
import com.airbnb.android.core.requests.constants.CalendarRulesRequestConstants;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.viewcomponents.models.InlineFormattedIntegerInputRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.utils.IntegerNumberFormatHelper;
import com.google.common.base.Objects;
import icepick.State;
import org.json.JSONException;
import org.json.JSONObject;

public class TripLengthSettingsHelper {
    private final InlineFormattedIntegerInputRowEpoxyModel_ maxNightsInputRow;
    @State
    Integer maximumNights;
    private final InlineFormattedIntegerInputRowEpoxyModel_ minNightsInputRow;
    @State
    Integer minimumNights;
    @State
    Integer weekendMinNights;
    private final InlineFormattedIntegerInputRowEpoxyModel_ weekendMinNightsInputRow;

    public interface Listener {
        void modelsUpdated();
    }

    public TripLengthSettingsHelper(Listing listing, CalendarRule calendarRule, Listener listener, Bundle savedInstanceState) {
        onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState == null) {
            this.minimumNights = SanitizeUtils.nullIfZero(listing.getMinNights());
            this.maximumNights = SanitizeUtils.nullIfZero(getMaxNightsToShow(listing));
            this.weekendMinNights = SanitizeUtils.nullIfZero(calendarRule.getWeekendMinNightsSetting().getMinNights());
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        this.minNightsInputRow = new InlineFormattedIntegerInputRowEpoxyModel_().titleRes(C7213R.string.manage_listing_trip_length_min_stay_title).hintRes(C7213R.string.manage_listing_trip_length_input_input_empty_hint).numberFormat(IntegerNumberFormatHelper.forInteger(4)).inputAmount(this.minimumNights).amountChangedListener(TripLengthSettingsHelper$$Lambda$1.lambdaFactory$(this, listener)).removeHintOnFocusMode(true);
        this.maxNightsInputRow = new InlineFormattedIntegerInputRowEpoxyModel_().titleRes(C7213R.string.manage_listing_trip_length_max_stay_title).hintRes(C7213R.string.manage_listing_trip_length_input_input_empty_hint).numberFormat(IntegerNumberFormatHelper.forInteger(5)).inputAmount(this.maximumNights).amountChangedListener(TripLengthSettingsHelper$$Lambda$2.lambdaFactory$(this, listener)).removeHintOnFocusMode(true);
        this.weekendMinNightsInputRow = new InlineFormattedIntegerInputRowEpoxyModel_().titleRes(C7213R.string.manage_listing_trip_length_weekend_min_stay_title).subTitleRes(C7213R.string.manage_listing_trip_length_weekend_min_stay_subtitle).hintRes(C7213R.string.manage_listing_trip_length_input_input_empty_hint).numberFormat(IntegerNumberFormatHelper.forInteger(5)).inputAmount(this.weekendMinNights).amountChangedListener(TripLengthSettingsHelper$$Lambda$3.lambdaFactory$(this, listener)).removeHintOnFocusMode(true);
    }

    static /* synthetic */ void lambda$new$0(TripLengthSettingsHelper tripLengthSettingsHelper, Listener listener, Integer inputAmount) {
        tripLengthSettingsHelper.minimumNights = inputAmount;
        listener.modelsUpdated();
    }

    static /* synthetic */ void lambda$new$1(TripLengthSettingsHelper tripLengthSettingsHelper, Listener listener, Integer inputAmount) {
        tripLengthSettingsHelper.maximumNights = inputAmount;
        listener.modelsUpdated();
    }

    static /* synthetic */ void lambda$new$2(TripLengthSettingsHelper tripLengthSettingsHelper, Listener listener, Integer inputAmount) {
        tripLengthSettingsHelper.weekendMinNights = inputAmount;
        listener.modelsUpdated();
    }

    public static int getMaxNightsToShow(Listing listing) {
        if (listing.getMaxNights() != 1125) {
            return listing.getMaxNights();
        }
        return 0;
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void onRestoreInstanceState(Bundle inState) {
        IcepickWrapper.restoreInstanceState(this, inState);
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ getMinNightsInputRow() {
        return this.minNightsInputRow;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ getMaxNightsInputRow() {
        return this.maxNightsInputRow;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ getWeekendMinNightsInputRow() {
        return this.weekendMinNightsInputRow;
    }

    public void setEnabled(boolean enabled) {
        this.minNightsInputRow.enabled(enabled);
        this.maxNightsInputRow.enabled(enabled);
        this.weekendMinNightsInputRow.enabled(enabled);
    }

    public Integer getMinimumNights() {
        return this.minimumNights;
    }

    public Integer getMaximumNights() {
        return this.maximumNights;
    }

    public int getRequestMinNights() {
        return SanitizeUtils.zeroIfNull(getMinNightsInputRow().inputAmount());
    }

    public int getRequestMaxNights() {
        int maxNights = SanitizeUtils.zeroIfNull(getMaxNightsInputRow().inputAmount());
        return maxNights != 0 ? maxNights : CalendarRulesRequestConstants.DEFAULT_MAX_NIGHTS;
    }

    public int getWeekendMinNights() {
        return SanitizeUtils.zeroIfNull(this.weekendMinNightsInputRow.inputAmount());
    }

    public Strap getUpdateListingSettings() {
        Strap strap = Strap.make();
        int minNights = getRequestMinNights();
        strap.mo11639kv(ListingRequestConstants.JSON_MIN_NIGHTS_KEY, String.valueOf(Math.max(1, minNights)));
        int maxNights = getRequestMaxNights();
        strap.mo11639kv(ListingRequestConstants.JSON_MAX_NIGHTS_KEY, String.valueOf(maxNights > 0 ? Integer.valueOf(Math.max(minNights, maxNights)) : null));
        return strap;
    }

    public JSONObject getCalendarRulesUpdateSettings(JSONObject requestHash) {
        try {
            requestHash.put(CalendarRulesRequestConstants.WEEKEND_MIN_NIGHTS, CalendarRulesRequest.getWeekendMinNightsHash(getWeekendMinNights()));
            return requestHash;
        } catch (JSONException e) {
            throw new RuntimeException("Error constructing JSON for weekend_min_nights update", e);
        }
    }

    public boolean hasChanged(Listing listing, CalendarRule calendarRule) {
        return !Objects.equal(this.minimumNights, SanitizeUtils.nullIfZero(listing.getMinNights())) || !Objects.equal(this.maximumNights, SanitizeUtils.nullIfZero(getMaxNightsToShow(listing))) || !Objects.equal(this.weekendMinNights, SanitizeUtils.nullIfZero(calendarRule.getWeekendMinNightsSetting().getMinNights()));
    }

    private void clearErrors() {
        this.minNightsInputRow.showError(false);
        this.maxNightsInputRow.showError(false);
        this.weekendMinNightsInputRow.showError(false);
    }

    public boolean validateInputsAndShowError() {
        boolean minNightsValid;
        boolean maxNightsValid;
        boolean weekendMinNightsValid;
        boolean minMaxNightsValid;
        if (this.minimumNights == null || this.minimumNights.intValue() <= 0) {
            minNightsValid = false;
        } else {
            minNightsValid = true;
        }
        if (this.maximumNights == null || (this.minimumNights != null && this.minimumNights.intValue() <= this.maximumNights.intValue())) {
            maxNightsValid = true;
        } else {
            maxNightsValid = false;
        }
        if (this.weekendMinNights == null || this.maximumNights == null || this.weekendMinNights.intValue() <= this.maximumNights.intValue()) {
            weekendMinNightsValid = true;
        } else {
            weekendMinNightsValid = false;
        }
        if (!minNightsValid || !maxNightsValid) {
            minMaxNightsValid = false;
        } else {
            minMaxNightsValid = true;
        }
        clearErrors();
        if (!minMaxNightsValid) {
            this.minNightsInputRow.showError(true);
            this.maxNightsInputRow.showError(true);
        } else if (!weekendMinNightsValid) {
            this.weekendMinNightsInputRow.showError(true);
            this.maxNightsInputRow.showError(true);
        }
        if (!minMaxNightsValid || !weekendMinNightsValid) {
            return false;
        }
        return true;
    }
}
