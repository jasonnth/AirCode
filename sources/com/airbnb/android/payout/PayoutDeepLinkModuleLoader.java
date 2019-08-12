package com.airbnb.android.payout;

import com.airbnb.android.payout.create.AddPayoutMethodActivity;
import com.airbnb.deeplinkdispatch.DeepLinkEntry;
import com.airbnb.deeplinkdispatch.DeepLinkEntry.Type;
import com.airbnb.deeplinkdispatch.Parser;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class PayoutDeepLinkModuleLoader implements Parser {
    public static final List<DeepLinkEntry> REGISTRY = Collections.unmodifiableList(Arrays.asList(new DeepLinkEntry[]{new DeepLinkEntry("airbnb://debug/add_payout_method", Type.CLASS, AddPayoutMethodActivity.class, null)}));

    public DeepLinkEntry parseUri(String uri) {
        for (DeepLinkEntry entry : REGISTRY) {
            if (entry.matches(uri)) {
                return entry;
            }
        }
        return null;
    }
}
