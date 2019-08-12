package com.appboy.models.cards;

import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import org.json.JSONObject;
import p004bo.app.C0365ax;
import p004bo.app.C0444dh;
import p004bo.app.C0460ds;

public final class ShortNewsCard extends Card {

    /* renamed from: j */
    private final String f2878j;

    /* renamed from: k */
    private final String f2879k;

    /* renamed from: l */
    private final String f2880l;

    /* renamed from: m */
    private final String f2881m;

    /* renamed from: n */
    private final String f2882n;

    public ShortNewsCard(JSONObject object, C0365ax manager, C0444dh feedStorageProvider) {
        super(object, manager, feedStorageProvider);
        this.f2878j = object.getString("description");
        this.f2879k = object.getString(ContentFrameworkAnalytics.IMAGE);
        this.f2880l = C0460ds.m529a(object, "title");
        this.f2881m = C0460ds.m529a(object, "url");
        this.f2882n = C0460ds.m529a(object, "domain");
    }

    public String getDescription() {
        return this.f2878j;
    }

    public String getImageUrl() {
        return this.f2879k;
    }

    public String getTitle() {
        return this.f2880l;
    }

    public String getUrl() {
        return this.f2881m;
    }

    public String getDomain() {
        return this.f2882n;
    }

    public String toString() {
        return "ShortNewsCard{mId='" + this.f2856c + '\'' + ", mViewed='" + this.f2857d + '\'' + ", mCreated='" + this.f2859f + '\'' + ", mUpdated='" + this.f2860g + '\'' + ", mDescription='" + this.f2878j + '\'' + ", mImageUrl='" + this.f2879k + '\'' + ", mTitle='" + this.f2880l + '\'' + ", mUrl='" + this.f2881m + '\'' + ", mDomain='" + this.f2882n + '\'' + "}";
    }
}
