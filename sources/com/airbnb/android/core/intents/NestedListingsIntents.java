package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.NestedListing;
import java.util.ArrayList;

public class NestedListingsIntents {
    public static final String INTENT_NESTED_LISTINGS = "nested_listing";

    public static class DeepLinks {
        public static Intent deepLinkIntentForNestedListings(Context context, Bundle extras) {
            return new Intent(context, Activities.nestedListings());
        }
    }

    public static Intent intentWithNestedListings(Context context, ArrayList<NestedListing> nestedListings) {
        return new Intent(context, Activities.nestedListings()).putParcelableArrayListExtra(INTENT_NESTED_LISTINGS, nestedListings);
    }
}
