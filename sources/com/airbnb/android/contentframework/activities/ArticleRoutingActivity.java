package com.airbnb.android.contentframework.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.contentframework.ContentFrameworkUtil;
import com.airbnb.android.contentframework.requests.GetArticleRequest;
import com.airbnb.android.contentframework.responses.GetArticleResponse;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.utils.BundleBuilder;
import p032rx.Observer;

public final class ArticleRoutingActivity extends AirActivity {
    private static final String ARG_ARTICLE_ID = "article_id";
    private static final String ARG_REFERRER = "referrer";
    private long articleId;
    private String referrer;
    final RequestListener<GetArticleResponse> requestListener = new C0699RL().onResponse(ArticleRoutingActivity$$Lambda$1.lambdaFactory$(this)).build();

    public static Intent intentForArticleId(Context context, long articleId2, String referrer2) {
        return new Intent(context, ArticleRoutingActivity.class).putExtras(((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString(ARG_REFERRER, referrer2)).putLong(ARG_ARTICLE_ID, articleId2)).toBundle());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (DeepLinkUtils.isDeepLink(intent)) {
            this.articleId = DeepLinkUtils.getParamAsId(intent, "id", ARG_ARTICLE_ID, "story_id");
            this.referrer = "deep_link";
        } else {
            this.articleId = intent.getLongExtra(ARG_ARTICLE_ID, -1);
            this.referrer = intent.getStringExtra(ARG_REFERRER);
        }
        fetchArticle(this.articleId);
    }

    /* access modifiers changed from: private */
    public void viewArticleNatively(Article article) {
        startActivity(ContentFrameworkUtil.intentForArticle(this, article, this.referrer));
        finish();
    }

    private void fetchArticle(long articleId2) {
        new GetArticleRequest(articleId2).withListener((Observer) this.requestListener).execute(this.requestManager);
    }
}
