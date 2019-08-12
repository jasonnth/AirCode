package com.airbnb.android.managelisting.settings.utils;

import android.content.Context;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineTipRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.settings.ManageListingActionExecutor;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;

public class CalendarRowUtils {
    public static List<EpoxyModel<?>> getCalendarRows(Context context, Listing listing, CalendarRule calendarRule, ManageListingActionExecutor actionExecutor, HashMap<Long, NestedListing> nestedListingsById, boolean useDocumentMarquee) {
        EpoxyModel calendarTitleRow;
        if (useDocumentMarquee) {
            calendarTitleRow = new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.manage_listing_booking_item_calendar_title);
        } else {
            calendarTitleRow = new SectionHeaderEpoxyModel_().titleRes(C7368R.string.manage_listing_booking_item_calendar_title);
        }
        List<EpoxyModel<?>> calendarMenuRows = Lists.newArrayList((E[]) new EpoxyModel[]{calendarTitleRow, new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_availability_rules).subtitle((CharSequence) ListingTextUtils.getAvailabilityDescriptionText(context, calendarRule)).clickListener(CalendarRowUtils$$Lambda$1.lambdaFactory$(actionExecutor)).disclosure(), new InlineTipRowEpoxyModel_().withNoTopPaddingLayout().textRes(C7368R.string.manage_listing_availability_settings_tip).linkRes(C7368R.string.learn_more_info_text).clickListener(CalendarRowUtils$$Lambda$2.lambdaFactory$(actionExecutor)), new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_trip_length).clickListener(CalendarRowUtils$$Lambda$3.lambdaFactory$(actionExecutor)).disclosure(), new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_check_in_out).clickListener(CalendarRowUtils$$Lambda$4.lambdaFactory$(actionExecutor)).disclosure()});
        StandardRowEpoxyModel_ nestedListingRow = buildNestedListingRow(context, listing.getId(), nestedListingsById, actionExecutor);
        if (nestedListingRow != null) {
            calendarMenuRows.add(nestedListingRow);
        }
        return calendarMenuRows;
    }

    private static StandardRowEpoxyModel_ buildNestedListingRow(Context context, long listingId, HashMap<Long, NestedListing> nestedListingsById, ManageListingActionExecutor actionExecutor) {
        StandardRowEpoxyModel_ nestedListingRow = null;
        if (nestedListingsById != null && FeatureToggles.showNestedListings()) {
            NestedListing targetListing = (NestedListing) nestedListingsById.get(Long.valueOf(listingId));
            if (targetListing != null) {
                nestedListingRow = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_nested_listing).subtitle((CharSequence) ListingTextUtils.getNestedListingSubtitle(context, targetListing, nestedListingsById)).show(FeatureToggles.showNestedListings());
                if (nestedListingsById.keySet().size() > 1) {
                    nestedListingRow.clickListener(CalendarRowUtils$$Lambda$5.lambdaFactory$(actionExecutor));
                    nestedListingRow.disclosure();
                }
            }
        }
        return nestedListingRow;
    }
}
