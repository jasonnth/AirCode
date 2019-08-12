package com.appboy.models.cards;

import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.android.lib.adapters.VerificationsAdapter;
import com.airbnb.android.lib.fragments.managelisting.EditPriceFragment;
import com.appboy.enums.AppStore;
import com.appboy.support.AppboyLogger;
import org.json.JSONObject;
import p004bo.app.C0365ax;
import p004bo.app.C0444dh;
import p004bo.app.C0460ds;

public final class CrossPromotionSmallCard extends Card {

    /* renamed from: j */
    private static final String f2865j = AppboyLogger.getAppboyLogTag(CrossPromotionSmallCard.class);

    /* renamed from: k */
    private final String f2866k;

    /* renamed from: l */
    private final String f2867l;

    /* renamed from: m */
    private final String f2868m;

    /* renamed from: n */
    private final String f2869n;

    /* renamed from: o */
    private double f2870o;

    /* renamed from: p */
    private int f2871p;

    /* renamed from: q */
    private final double f2872q;

    /* renamed from: r */
    private final String f2873r;

    /* renamed from: s */
    private String f2874s;

    /* renamed from: t */
    private String f2875t;

    /* renamed from: u */
    private AppStore f2876u;

    /* renamed from: v */
    private String f2877v;

    public CrossPromotionSmallCard(JSONObject object, C0365ax manager, C0444dh feedStorageProvider) {
        super(object, manager, feedStorageProvider);
        this.f2866k = object.getString("title");
        this.f2867l = object.getString("subtitle");
        this.f2868m = object.getString("caption");
        this.f2869n = object.getString(ContentFrameworkAnalytics.IMAGE);
        try {
            this.f2870o = object.getDouble(UpdateReviewRequest.KEY_OVERALL);
            this.f2871p = object.getInt(VerificationsAdapter.VERIFICATION_REVIEWS);
        } catch (Exception e) {
            this.f2870o = 0.0d;
            this.f2871p = 0;
        }
        if (object.has("package")) {
            this.f2874s = object.getString("package");
        }
        if (object.has("kindle_id")) {
            this.f2875t = object.getString("kindle_id");
        }
        this.f2872q = object.getDouble(EditPriceFragment.RESULT_PRICE);
        if (object.has("display_price")) {
            this.f2877v = object.getString("display_price");
        }
        this.f2873r = object.getString("url");
        if (C0460ds.m529a(object, "store") != null) {
            try {
                String a = C0460ds.m529a(object, "store");
                if (a != null) {
                    this.f2876u = AppStore.valueOf(AppStore.serverStringToEnumString(a));
                } else {
                    this.f2876u = AppStore.GOOGLE_PLAY_STORE;
                }
            } catch (Exception e2) {
                AppboyLogger.m1736e(f2865j, "Caught exception creating cross promotion small card Json.", e2);
                this.f2876u = AppStore.GOOGLE_PLAY_STORE;
            }
        }
    }

    public String getTitle() {
        return this.f2866k;
    }

    public String getSubtitle() {
        return this.f2867l;
    }

    public String getCaption() {
        return this.f2868m;
    }

    public String getImageUrl() {
        return this.f2869n;
    }

    public double getRating() {
        return this.f2870o;
    }

    public int getReviewCount() {
        return this.f2871p;
    }

    public double getPrice() {
        return this.f2872q;
    }

    public String getUrl() {
        return this.f2873r;
    }

    public String getPackage() {
        return this.f2874s;
    }

    public String getKindleId() {
        return this.f2875t;
    }

    public AppStore getAppStore() {
        return this.f2876u;
    }

    public String getDisplayPrice() {
        return this.f2877v;
    }

    public String toString() {
        return "CrossPromotionSmallCard{mId='" + this.f2856c + '\'' + ", mViewed='" + this.f2857d + '\'' + ", mCreated='" + this.f2859f + '\'' + ", mUpdated='" + this.f2860g + '\'' + ", mTitle='" + this.f2866k + '\'' + ", mSubtitle='" + this.f2867l + '\'' + ", mCaption='" + this.f2868m + '\'' + ", mImageUrl='" + this.f2869n + '\'' + ", mRating=" + this.f2870o + ", mReviewCount=" + this.f2871p + ", mPrice=" + this.f2872q + ", mPackage=" + this.f2874s + ", mUrl='" + this.f2873r + '\'' + ", mAppStore='" + this.f2876u + '\'' + ", mKindleId='" + this.f2875t + '\'' + ", mDisplayPrice='" + this.f2877v + '\'' + "}";
    }
}
