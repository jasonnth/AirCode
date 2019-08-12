package com.airbnb.android.lib.content;

import android.content.Intent;
import com.airbnb.android.utils.content.DeepLinkParser;

public class VerifiedIdDeepLinkParser extends DeepLinkParser {
    public VerifiedIdDeepLinkParser(Intent intent) {
        super(intent);
    }

    public boolean isValid() {
        return false;
    }
}
