package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.SeasonalMinNightsCalendarSetting;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.listing.adapters.TripLengthSettingsHelper;
import com.airbnb.android.listing.utils.SeasonalSettingsDisplay;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;

public class ManageListingTripLengthAdapter extends AirEpoxyAdapter {
    private final LinkActionRowEpoxyModel_ addNewSettingLinkRow = new LinkActionRowEpoxyModel_().textRes(C7368R.string.manage_listing_trip_length_add_seasonal_requirement_link).clickListener(ManageListingTripLengthAdapter$$Lambda$1.lambdaFactory$(this));
    private final Context context;
    private final TripLengthSettingsHelper helper;
    private final Listener listener;
    @State
    ArrayList<SeasonalMinNightsCalendarSetting> seasonalRequirements;
    private final SectionHeaderEpoxyModel_ seasonalSettingsHeader = new SectionHeaderEpoxyModel_().titleRes(C7368R.string.manage_listing_trip_length_seasonal_requirement_title);

    public interface Listener {
        void onModifySeasonalRequirement(SeasonalMinNightsCalendarSetting seasonalMinNightsCalendarSetting);

        void settingsAreValid(boolean z);
    }

    public ManageListingTripLengthAdapter(Context context2, Listing listing, CalendarRule calendarRule, Listener listener2, Bundle savedInstanceState) {
        super(true);
        this.listener = listener2;
        this.context = context2;
        enableDiffing();
        this.helper = new TripLengthSettingsHelper(listing, calendarRule, ManageListingTripLengthAdapter$$Lambda$2.lambdaFactory$(this), savedInstanceState);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.manage_listing_trip_length_title), this.helper.getMinNightsInputRow(), this.helper.getMaxNightsInputRow(), this.helper.getWeekendMinNightsInputRow()});
    }

    public int getSeasonalSettingSectionPosition() {
        return getModelPosition(this.seasonalSettingsHeader);
    }

    public int getSeasonalRequirementsCount() {
        if (this.seasonalRequirements != null) {
            return this.seasonalRequirements.size();
        }
        return 0;
    }

    public void setSeasonalRequirements(ArrayList<SeasonalMinNightsCalendarSetting> seasonalRequirements2) {
        this.seasonalRequirements = seasonalRequirements2;
        removeAllAfterModel(this.helper.getWeekendMinNightsInputRow());
        if (!seasonalRequirements2.isEmpty()) {
            addModel(this.seasonalSettingsHeader);
            addModels((Collection<? extends EpoxyModel<?>>) FluentIterable.from((Iterable<E>) seasonalRequirements2).transform(ManageListingTripLengthAdapter$$Lambda$3.lambdaFactory$(this)).toList());
        }
        addModel(this.addNewSettingLinkRow);
    }

    public void setEnabled(boolean enabled) {
        this.helper.setEnabled(enabled);
        notifyModelsChanged();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.helper.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        this.helper.onRestoreInstanceState(inState);
    }

    public boolean hasChanged(Listing listing, CalendarRule calendarRule) {
        return this.helper.hasChanged(listing, calendarRule);
    }

    public int getMinNights() {
        return this.helper.getRequestMinNights();
    }

    public int getMaxNights() {
        return this.helper.getRequestMaxNights();
    }

    public int getWeekendMinNights() {
        return this.helper.getWeekendMinNights();
    }

    public void notifyValidSettingsListener() {
        boolean minNightsValid;
        boolean maxNightsValid;
        boolean z = true;
        int min = SanitizeUtils.zeroIfNull(this.helper.getMinimumNights());
        int max = SanitizeUtils.zeroIfNull(this.helper.getMaximumNights());
        if (min > 0) {
            minNightsValid = true;
        } else {
            minNightsValid = false;
        }
        if (max == 0 || max >= min) {
            maxNightsValid = true;
        } else {
            maxNightsValid = false;
        }
        Listener listener2 = this.listener;
        if (!minNightsValid || !maxNightsValid) {
            z = false;
        }
        listener2.settingsAreValid(z);
    }

    /* access modifiers changed from: private */
    public void notifyListener() {
        this.listener.onModifySeasonalRequirement(new SeasonalMinNightsCalendarSetting());
    }

    /* access modifiers changed from: private */
    public StandardRowEpoxyModel_ createRowFromSeasonalSetting(Context context2, SeasonalMinNightsCalendarSetting setting) {
        String dayFormat = context2.getString(C7368R.string.mdy_format_shorter);
        String startDate = setting.getStartDate().formatDate(dayFormat);
        String endEdate = setting.getEndDate().formatDate(dayFormat);
        String title = context2.getString(C7368R.string.calendar_setting_date_range, new Object[]{startDate, endEdate});
        return new StandardRowEpoxyModel_().title((CharSequence) title).subtitle((CharSequence) SeasonalSettingsDisplay.getSeasonalRequirementDescription(context2, setting)).disclosure().clickListener(ManageListingTripLengthAdapter$$Lambda$4.lambdaFactory$(this, setting));
    }
}
