package com.airbnb.android.nestedlistings;

import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.models.NestedListing;

public interface NestedListingsActionExecutor {
    void nestedListingsChildren(NestedListing nestedListing, boolean z);

    void nestedListingsParent();

    void popToFragment(Class<? extends Fragment> cls);
}
