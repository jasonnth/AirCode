package com.airbnb.android.contentframework;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.contentframework.fragments.StoryDetailViewFragment;
import com.airbnb.android.core.models.Article;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ContentFrameworkUtil {
    public static final String DEEP_LINK = "deep_link";
    public static final String GUEST_HOME = "guest_home";
    public static final String SIMPLE_ARTICLE = "simple_article";
    public static final String STORY_BACKGROUND_PUBLISHER = "story_background_publisher";
    public static final String STORY_FEED = "story_feed";
    public static final String WEB_LINK = "web_link";

    @Retention(RetentionPolicy.SOURCE)
    public @interface Referrer {
    }

    public static Intent intentForArticle(Context context, Article article, String referrer) {
        switch (article.getType()) {
            case Simple:
                return StoryDetailViewFragment.forPartialArticle(context, article, referrer);
            default:
                throw new IllegalArgumentException("unknown article type: " + article.getTypeStr());
        }
    }
}
