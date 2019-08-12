package com.airbnb.android.nestedlistings;

import com.airbnb.android.core.models.NestedListing;
import java.util.ArrayList;
import java.util.HashMap;

public interface NestedListingsController {
    NestedListingsActionExecutor getActionExecutor();

    ArrayList<NestedListing> getNestedListings();

    HashMap<Long, NestedListing> getNestedListingsById();

    void setNestedListingsById(HashMap<Long, NestedListing> hashMap);
}
