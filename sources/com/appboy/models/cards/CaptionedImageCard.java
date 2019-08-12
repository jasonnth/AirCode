package com.appboy.models.cards;

import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import org.json.JSONObject;
import p004bo.app.C0365ax;
import p004bo.app.C0444dh;
import p004bo.app.C0460ds;

public final class CaptionedImageCard extends Card {

    /* renamed from: j */
    private final String f2847j;

    /* renamed from: k */
    private final String f2848k;

    /* renamed from: l */
    private final String f2849l;

    /* renamed from: m */
    private final String f2850m;

    /* renamed from: n */
    private final String f2851n;

    /* renamed from: o */
    private final float f2852o;

    public CaptionedImageCard(JSONObject object, C0365ax manager, C0444dh feedStorageProvider) {
        super(object, manager, feedStorageProvider);
        this.f2847j = object.getString(ContentFrameworkAnalytics.IMAGE);
        this.f2848k = object.getString("title");
        this.f2849l = object.getString("description");
        this.f2850m = C0460ds.m529a(object, "url");
        this.f2851n = C0460ds.m529a(object, "domain");
        this.f2852o = (float) object.optDouble("aspect_ratio", 0.0d);
    }

    public String getImageUrl() {
        return this.f2847j;
    }

    public String getTitle() {
        return this.f2848k;
    }

    public String getDescription() {
        return this.f2849l;
    }

    public String getUrl() {
        return this.f2850m;
    }

    public String getDomain() {
        return this.f2851n;
    }

    public float getAspectRatio() {
        return this.f2852o;
    }

    public String toString() {
        return "CaptionedImageCard{mId='" + this.f2856c + '\'' + ", mViewed='" + this.f2857d + '\'' + ", mCreated='" + this.f2859f + '\'' + ", mUpdated='" + this.f2860g + '\'' + ", mImageUrl='" + this.f2847j + '\'' + ", mTitle='" + this.f2848k + '\'' + ", mDescription='" + this.f2849l + '\'' + ", mUrl='" + this.f2850m + '\'' + ", mDomain='" + this.f2851n + '\'' + ", mAspectRatio='" + this.f2852o + '\'' + "}";
    }
}
