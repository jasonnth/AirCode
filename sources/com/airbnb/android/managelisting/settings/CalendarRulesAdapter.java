package com.airbnb.android.managelisting.settings;

import android.content.Context;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.managelisting.settings.utils.CalendarRowUtils;
import com.airbnb.epoxy.EpoxyModel;
import java.util.Collection;
import java.util.HashMap;

public class CalendarRulesAdapter extends AirEpoxyAdapter {
    CalendarRulesAdapter(Context context, Listing listing, CalendarRule calendarRule, ManageListingActionExecutor actionExecutor, HashMap<Long, NestedListing> nestedListingsById) {
        addModels((Collection<? extends EpoxyModel<?>>) CalendarRowUtils.getCalendarRows(context, listing, calendarRule, actionExecutor, nestedListingsById, true));
    }
}
