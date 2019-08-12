package com.airbnb.android.explore;

import com.airbnb.deeplinkdispatch.DeepLinkEntry;
import com.airbnb.deeplinkdispatch.Parser;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ExploreDeepLinkModuleLoader implements Parser {
    public static final List<DeepLinkEntry> REGISTRY = Collections.unmodifiableList(Arrays.asList(new DeepLinkEntry[0]));

    public DeepLinkEntry parseUri(String uri) {
        for (DeepLinkEntry entry : REGISTRY) {
            if (entry.matches(uri)) {
                return entry;
            }
        }
        return null;
    }
}
