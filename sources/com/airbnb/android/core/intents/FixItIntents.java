package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;

public class FixItIntents {
    public static final String INTENT_EXTRA_LISTING_NAME = "listing_name";
    public static final String INTENT_EXTRA_REPORT_ID = "report_id";

    public static Intent intentForReportId(Context context, long reportId, String listingName) {
        return new Intent(context, Activities.fixItReport()).putExtra(INTENT_EXTRA_REPORT_ID, reportId).putExtra("listing_name", listingName);
    }
}
