package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.contentframework.ContentFrameworkUtil;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.Article.Type;
import com.airbnb.android.utils.BundleBuilder;

public final class ArticleIntents {
    private static final String ARG_ARTICLE_ID = "arg_article_id";
    private static final String ARG_REFERRER = "arg_referrer";
    private static final String EXTRA_BUNDLE = "bundle";
    private static final String EXTRA_FRAGMENT_CLS = "frag_cls";

    public static Intent intentForArticle(Context context, Article article) {
        Intent intent = createIntent(context);
        if (article.getType() == Type.Simple) {
            intent.putExtra(EXTRA_FRAGMENT_CLS, "com.airbnb.android.contentframework.fragments.ViewSimpleArticleFragment");
        }
        intent.putExtra("bundle", getBundle(article.getId(), ContentFrameworkUtil.GUEST_HOME));
        return intent;
    }

    private static Intent createIntent(Context context) {
        return new Intent(context, Activities.articles());
    }

    private static Bundle getBundle(long articleId, String referrer) {
        return ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong(ARG_ARTICLE_ID, articleId)).putString(ARG_REFERRER, referrer)).toBundle();
    }
}
