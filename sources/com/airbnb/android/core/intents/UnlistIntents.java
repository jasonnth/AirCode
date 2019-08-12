package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.Listing;

public class UnlistIntents {
    public static final String ARG_LISTING = "listing";

    public static Intent intentForListing(Context context, Listing listing) {
        Intent intent = new Intent(context, Activities.unlist());
        Bundle arguments = new Bundle();
        arguments.putParcelable("listing", listing);
        intent.putExtras(arguments);
        return intent;
    }
}
