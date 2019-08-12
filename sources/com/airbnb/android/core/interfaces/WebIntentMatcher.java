package com.airbnb.android.core.interfaces;

import android.content.Context;
import android.net.Uri;
import com.airbnb.android.core.utils.webintent.WebIntentMatcherResult;

public interface WebIntentMatcher {
    WebIntentMatcherResult forUri(Context context, Uri uri);
}
