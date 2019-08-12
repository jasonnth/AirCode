package com.airbnb.android.lib.deeplinks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.deeplinks.WebLink;
import com.airbnb.android.core.intents.HostCalendarIntents;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.bugsnag.android.Severity;

public class WebLinkManageListingIntents {
    private static final String AVAILABILITY_SETTINGS = "availability";
    private static final String LONG_TERM_SECTION = "long_term";
    private static final String NEW_HOST_PRICE_REDUCTION_REF = "new_host_price_reduction";
    private static final String PRICING_SETTINGS = "pricing";

    @WebLink({"manage-listing/{deep_link_listing_id}/calendar"})
    public static Intent handlePricingScreen(Context context, Bundle bundle) {
        String section = bundle.getString(BaseAnalytics.SECTION);
        String settings = bundle.getString("settings");
        String ref = bundle.getString("ref");
        Long listingId = DeepLinkUtils.getLongParam(bundle, "deep_link_listing_id");
        if (listingId == null) {
            BugsnagWrapper.notify((Throwable) new IllegalArgumentException("listing id is null"));
            Toast.makeText(context, "No listing id was specified!", 0).show();
            return HomeActivityIntents.intentForDefaultTab(context);
        } else if (LONG_TERM_SECTION.equals(section)) {
            return ManageListingIntents.intentForExistingListingSetting(context, listingId.longValue(), SettingDeepLink.LongTermPricing);
        } else {
            if (PRICING_SETTINGS.equals(settings) || NEW_HOST_PRICE_REDUCTION_REF.equals(ref)) {
                return ManageListingIntents.intentForExistingListingSetting(context, listingId.longValue(), SettingDeepLink.Price);
            }
            if (AVAILABILITY_SETTINGS.equals(settings)) {
                return getCalendarIntent(context, listingId.longValue());
            }
            BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Did not match this link to a specific pricing screen: " + bundle.getString("deep_link_uri")), Severity.WARNING);
            return getCalendarIntent(context, listingId.longValue());
        }
    }

    private static Intent getCalendarIntent(Context context, long listingId) {
        return HostCalendarIntents.intentForSingleListingCalendarDeepLink(context, listingId);
    }
}
