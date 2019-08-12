package com.airbnb.android.registration;

import com.airbnb.deeplinkdispatch.DeepLinkEntry;
import com.airbnb.deeplinkdispatch.DeepLinkEntry.Type;
import com.airbnb.deeplinkdispatch.Parser;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class RegistrationDeepLinkModuleLoader implements Parser {
    public static final List<DeepLinkEntry> REGISTRY = Collections.unmodifiableList(Arrays.asList(new DeepLinkEntry[]{new DeepLinkEntry("airbnb://debug/social_sign_up", Type.METHOD, CreateSocialAccountFragment.class, "newIntentForDebug")}));

    public DeepLinkEntry parseUri(String uri) {
        for (DeepLinkEntry entry : REGISTRY) {
            if (entry.matches(uri)) {
                return entry;
            }
        }
        return null;
    }
}
