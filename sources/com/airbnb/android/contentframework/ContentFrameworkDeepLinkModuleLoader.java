package com.airbnb.android.contentframework;

import com.airbnb.android.contentframework.activities.ArticleRoutingActivity;
import com.airbnb.android.contentframework.activities.PlayVideoActivity;
import com.airbnb.android.contentframework.fragments.StoryCreationPickTripFragment;
import com.airbnb.deeplinkdispatch.DeepLinkEntry;
import com.airbnb.deeplinkdispatch.DeepLinkEntry.Type;
import com.airbnb.deeplinkdispatch.Parser;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ContentFrameworkDeepLinkModuleLoader implements Parser {
    public static final List<DeepLinkEntry> REGISTRY = Collections.unmodifiableList(Arrays.asList(new DeepLinkEntry[]{new DeepLinkEntry("airbnb://d/content/articles/{id}", Type.CLASS, ArticleRoutingActivity.class, null), new DeepLinkEntry("airbnb://d/content/stories/{id}", Type.CLASS, ArticleRoutingActivity.class, null), new DeepLinkEntry("airbnb://d/story/composer_pick_trip", Type.METHOD, StoryCreationPickTripFragment.class, "newIntent"), new DeepLinkEntry("airbnb://d/content/articles", Type.CLASS, ArticleRoutingActivity.class, null), new DeepLinkEntry("airbnb://d/content/stories", Type.CLASS, ArticleRoutingActivity.class, null), new DeepLinkEntry("airbnb://d/videos", Type.CLASS, PlayVideoActivity.class, null)}));

    public DeepLinkEntry parseUri(String uri) {
        for (DeepLinkEntry entry : REGISTRY) {
            if (entry.matches(uri)) {
                return entry;
            }
        }
        return null;
    }
}
