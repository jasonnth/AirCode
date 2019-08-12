package com.airbnb.android.sharing.shareables;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.StoryElement;
import com.airbnb.android.core.models.StoryElement.Type;
import com.airbnb.android.core.utils.LocationUtil;
import com.airbnb.android.core.utils.WeChatHelper;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.android.sharing.enums.CustomShareActivities;
import p032rx.Observable;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.schedulers.Schedulers;

public class StoryShareable extends Shareable {
    private static final String ARTICLE_BASE_URL = (LocationUtil.isUserPreferredCountryChina() ? "https://zh.airbnb.com/content/stories/" : "https://www.airbnb.com/content/stories/");
    private final String coverImageUrl;
    private final String description;

    /* renamed from: id */
    private final long f1863id;
    /* access modifiers changed from: private */
    public Bitmap thumbnailBitmap;
    private final String title;

    public StoryShareable(Context context, Article article) {
        super(context);
        this.f1863id = article.getId();
        this.title = article.getTitle();
        this.description = getDescription(article);
        this.coverImageUrl = article.getCoverImageUrl();
        Observable.fromCallable(StoryShareable$$Lambda$1.lambdaFactory$(this, context)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(StoryShareable$$Lambda$2.lambdaFactory$(this));
    }

    private static String getDescription(Article article) {
        for (StoryElement element : article.getElements()) {
            if (element.getType() == Type.Text) {
                String desc = element.getText();
                if (desc.length() < WeChatHelper.DESCRIPTION_LENGTH_LIMIT) {
                    return desc;
                }
                return desc.substring(0, WeChatHelper.DESCRIPTION_LENGTH_LIMIT);
            }
        }
        return null;
    }

    public String getName() {
        return this.title;
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        return ARTICLE_BASE_URL + this.f1863id;
    }

    public String getImageUrl() {
        return this.coverImageUrl;
    }

    public String getDeeplinkPath() {
        return "d/content/stories/" + this.f1863id;
    }

    public Intent buildIntentForPackage(Intent baseIntent, CustomShareActivities csa, String packageName) {
        AirbnbEventLogger.event().name("sharing").mo8238kv(BaseAnalytics.OPERATION, "click").mo8238kv(BaseAnalytics.TARGET, "share_button").mo8238kv(ShareActivityIntents.ARG_ENTRY_POINT, "story").mo8237kv("story_id", this.f1863id).mo8238kv("service", packageName).track();
        String articleUrl = buildShareUriString(csa);
        switch (csa) {
            case COPY_TO_CLIPBOARD:
                return baseIntent.putExtra("android.intent.extra.TEXT", articleUrl);
            case WECHAT:
                WeChatHelper.shareWebPageToWechat(this.context, this.title, this.description, articleUrl, getThumbnailBitmap(), null);
                return null;
            default:
                baseIntent.putExtra("android.intent.extra.SUBJECT", this.title);
                baseIntent.putExtra("android.intent.extra.TEXT", articleUrl);
                return baseIntent;
        }
    }

    private Bitmap getThumbnailBitmap() {
        if (this.thumbnailBitmap != null) {
            return this.thumbnailBitmap;
        }
        return BitmapFactory.decodeResource(this.context.getResources(), C0921R.C0922drawable.airbnb_logo_white_bg);
    }
}
