package com.appboy.models.cards;

import org.json.JSONObject;
import p004bo.app.C0365ax;
import p004bo.app.C0444dh;
import p004bo.app.C0460ds;

public final class TextAnnouncementCard extends Card {

    /* renamed from: j */
    private final String f2883j;

    /* renamed from: k */
    private final String f2884k;

    /* renamed from: l */
    private final String f2885l;

    /* renamed from: m */
    private final String f2886m;

    public TextAnnouncementCard(JSONObject object, C0365ax manager, C0444dh feedStorageProvider) {
        super(object, manager, feedStorageProvider);
        this.f2884k = C0460ds.m529a(object, "title");
        this.f2883j = object.getString("description");
        this.f2885l = C0460ds.m529a(object, "url");
        this.f2886m = C0460ds.m529a(object, "domain");
    }

    public String getDescription() {
        return this.f2883j;
    }

    public String getTitle() {
        return this.f2884k;
    }

    public String getUrl() {
        return this.f2885l;
    }

    public String getDomain() {
        return this.f2886m;
    }

    public String toString() {
        return "TextAnnouncementCard{mId='" + this.f2856c + '\'' + ", mViewed='" + this.f2857d + '\'' + ", mCreated='" + this.f2859f + '\'' + ", mUpdated='" + this.f2860g + '\'' + ", mDescription='" + this.f2883j + '\'' + ", mTitle='" + this.f2884k + '\'' + ", mUrl='" + this.f2885l + '\'' + ", mDomain='" + this.f2886m + '\'' + "}";
    }
}
