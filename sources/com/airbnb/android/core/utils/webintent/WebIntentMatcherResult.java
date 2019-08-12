package com.airbnb.android.core.utils.webintent;

import android.content.Intent;
import android.net.Uri;
import com.airbnb.android.core.utils.Check;

public class WebIntentMatcherResult {
    private final Intent intent;
    private final Path path;
    private final Uri uri;

    public WebIntentMatcherResult(Uri uri2, Path path2, Intent intent2) {
        this.uri = uri2;
        this.path = path2;
        this.intent = intent2;
    }

    public Uri getUri() {
        return this.uri;
    }

    public boolean hasPathMatch() {
        return this.path != null;
    }

    public Path getPath() {
        return (Path) Check.notNull(this.path);
    }

    public boolean hasIntentMatch() {
        return this.intent != null;
    }

    public Intent getIntent() {
        return (Intent) Check.notNull(this.intent);
    }
}
