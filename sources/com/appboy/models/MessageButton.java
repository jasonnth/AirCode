package com.appboy.models;

import android.net.Uri;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import p004bo.app.C0460ds;

public class MessageButton implements IPutIntoJson<JSONObject> {

    /* renamed from: a */
    private static final String f2833a = AppboyLogger.getAppboyLogTag(MessageButton.class);

    /* renamed from: b */
    private JSONObject f2834b;

    /* renamed from: c */
    private int f2835c;

    /* renamed from: d */
    private ClickAction f2836d;

    /* renamed from: e */
    private Uri f2837e;

    /* renamed from: f */
    private String f2838f;

    /* renamed from: g */
    private int f2839g;

    /* renamed from: h */
    private int f2840h;

    public MessageButton() {
        this.f2835c = -1;
        this.f2836d = ClickAction.NONE;
        this.f2839g = 0;
        this.f2840h = 0;
    }

    public MessageButton(JSONObject object) {
        this(object, object.optInt("id", -1), (ClickAction) C0460ds.m528a(object, "click_action", ClickAction.class, ClickAction.NEWS_FEED), object.optString("uri"), object.optString("text"), object.optInt("bg_color"), object.optInt("text_color"));
    }

    private MessageButton(JSONObject jsonObject, int id, ClickAction clickAction, String uri, String text, int backgroundColor, int textColor) {
        this.f2835c = -1;
        this.f2836d = ClickAction.NONE;
        this.f2839g = 0;
        this.f2840h = 0;
        this.f2834b = jsonObject;
        this.f2835c = id;
        this.f2836d = clickAction;
        if (this.f2836d == ClickAction.URI && !StringUtils.isNullOrBlank(uri)) {
            this.f2837e = Uri.parse(uri);
        }
        this.f2838f = text;
        this.f2839g = backgroundColor;
        this.f2840h = textColor;
    }

    public int getId() {
        return this.f2835c;
    }

    public ClickAction getClickAction() {
        return this.f2836d;
    }

    public Uri getUri() {
        return this.f2837e;
    }

    public String getText() {
        return this.f2838f;
    }

    public int getBackgroundColor() {
        return this.f2839g;
    }

    public int getTextColor() {
        return this.f2840h;
    }

    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.f2835c);
            jSONObject.put("click_action", this.f2836d.toString());
            if (this.f2837e != null) {
                jSONObject.put("uri", this.f2837e.toString());
            }
            jSONObject.putOpt("text", this.f2838f);
            jSONObject.put("bg_color", this.f2839g);
            jSONObject.put("text_color", this.f2840h);
            return jSONObject;
        } catch (JSONException e) {
            return this.f2834b;
        }
    }
}
