package com.appboy.models.cards;

import com.appboy.enums.CardCategory;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import org.json.JSONArray;
import org.json.JSONObject;
import p004bo.app.C0375bd;
import p004bo.app.C0386bo;
import p004bo.app.C0397bz;
import p004bo.app.C0444dh;
import p004bo.app.C0455dp;
import p004bo.app.C0460ds;

public class Card extends Observable implements IPutIntoJson<JSONObject> {

    /* renamed from: j */
    private static final String f2853j = String.format("%s.%s", new Object[]{"Appboy", Card.class.getName()});

    /* renamed from: a */
    protected final JSONObject f2854a;

    /* renamed from: b */
    protected final Map<String, String> f2855b;

    /* renamed from: c */
    protected final String f2856c;

    /* renamed from: d */
    protected boolean f2857d;

    /* renamed from: e */
    protected boolean f2858e = this.f2857d;

    /* renamed from: f */
    protected final long f2859f;

    /* renamed from: g */
    protected final long f2860g;

    /* renamed from: h */
    protected final long f2861h;

    /* renamed from: i */
    protected final EnumSet<CardCategory> f2862i;

    /* renamed from: k */
    private final C0375bd f2863k;

    /* renamed from: l */
    private final C0444dh f2864l;

    public Card(JSONObject object, C0375bd manager, C0444dh storageProvider) {
        this.f2854a = object;
        this.f2855b = C0460ds.m530a(object.optJSONObject("extras"), (Map<String, String>) new HashMap<String,String>());
        this.f2863k = manager;
        this.f2864l = storageProvider;
        this.f2856c = object.getString("id");
        this.f2857d = object.getBoolean("viewed");
        this.f2859f = object.getLong("created");
        this.f2860g = object.getLong("updated");
        this.f2861h = object.optLong("expires_at", -1);
        JSONArray optJSONArray = object.optJSONArray("categories");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            this.f2862i = EnumSet.of(CardCategory.NO_CATEGORY);
            return;
        }
        this.f2862i = EnumSet.noneOf(CardCategory.class);
        for (int i = 0; i < optJSONArray.length(); i++) {
            CardCategory cardCategory = CardCategory.get(optJSONArray.getString(i));
            if (cardCategory != null) {
                this.f2862i.add(cardCategory);
            }
        }
    }

    public boolean logImpression() {
        try {
            if (!(this.f2863k == null || this.f2864l == null || !mo27344a())) {
                this.f2863k.mo6769a((C0386bo) C0397bz.m299c(this.f2856c));
                this.f2864l.mo6958a(this.f2856c);
                return true;
            }
        } catch (Exception e) {
            AppboyLogger.m1740w(f2853j, "Failed to log feed card impression.", e);
        }
        return false;
    }

    public boolean logClick() {
        try {
            if (this.f2863k != null && mo27344a()) {
                this.f2863k.mo6769a((C0386bo) C0397bz.m302d(this.f2856c));
                return true;
            }
        } catch (Exception e) {
            AppboyLogger.m1740w(f2853j, "Failed to log feed card clicked.", e);
        }
        return false;
    }

    public boolean isEqualToCard(Card card) {
        return this.f2856c.equals(card.getId()) && this.f2860g == card.getUpdated() && this.f2863k == card.f2863k;
    }

    public String getId() {
        return this.f2856c;
    }

    public String getUrl() {
        return null;
    }

    public boolean getViewed() {
        return this.f2857d;
    }

    public void setViewed(boolean viewed) {
        this.f2857d = viewed;
    }

    public boolean isRead() {
        return this.f2858e;
    }

    public void setIsRead(boolean read) {
        this.f2858e = read;
        setChanged();
        notifyObservers();
        if (read) {
            try {
                this.f2864l.mo6960b(this.f2856c);
            } catch (Exception e) {
                AppboyLogger.m1734d(f2853j, "Failed to mark card as read.", e);
            }
        }
    }

    public long getUpdated() {
        return this.f2860g;
    }

    public long getExpiresAt() {
        return this.f2861h;
    }

    public boolean isExpired() {
        return getExpiresAt() != -1 && getExpiresAt() <= C0455dp.m515a();
    }

    public boolean isInCategorySet(EnumSet<CardCategory> categories) {
        Iterator it = categories.iterator();
        while (it.hasNext()) {
            if (this.f2862i.contains((CardCategory) it.next())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public boolean mo27344a() {
        if (!StringUtils.isNullOrBlank(this.f2856c)) {
            return true;
        }
        AppboyLogger.m1735e(f2853j, "Card ID cannot be null");
        return false;
    }

    public JSONObject forJsonPut() {
        return this.f2854a;
    }
}
