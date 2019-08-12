package com.appboy.models.cards;

import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import org.json.JSONObject;
import p004bo.app.C0365ax;
import p004bo.app.C0444dh;
import p004bo.app.C0460ds;

public final class BannerImageCard extends Card {

    /* renamed from: j */
    private final String f2843j;

    /* renamed from: k */
    private final String f2844k;

    /* renamed from: l */
    private final String f2845l;

    /* renamed from: m */
    private final float f2846m;

    public BannerImageCard(JSONObject object, C0365ax manager, C0444dh feedStorageProvider) {
        super(object, manager, feedStorageProvider);
        this.f2843j = object.getString(ContentFrameworkAnalytics.IMAGE);
        this.f2844k = C0460ds.m529a(object, "url");
        this.f2845l = C0460ds.m529a(object, "domain");
        this.f2846m = (float) object.optDouble("aspect_ratio", 0.0d);
    }

    public String getImageUrl() {
        return this.f2843j;
    }

    public String getUrl() {
        return this.f2844k;
    }

    public float getAspectRatio() {
        return this.f2846m;
    }

    public String toString() {
        return "BannerImageCard{mId='" + this.f2856c + '\'' + ", mViewed='" + this.f2857d + '\'' + ", mCreated='" + this.f2859f + '\'' + ", mUpdated='" + this.f2860g + '\'' + ", mImageUrl='" + this.f2843j + '\'' + ", mUrl='" + this.f2844k + '\'' + ", mDomain='" + this.f2845l + '\'' + ", mAspectRatio='" + this.f2846m + '\'' + "}";
    }
}
