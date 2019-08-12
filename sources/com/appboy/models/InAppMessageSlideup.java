package com.appboy.models;

import com.appboy.enums.inappmessage.CropType;
import com.appboy.enums.inappmessage.MessageType;
import com.appboy.enums.inappmessage.SlideFrom;
import com.appboy.enums.inappmessage.TextAlign;
import org.json.JSONException;
import org.json.JSONObject;
import p004bo.app.C0375bd;
import p004bo.app.C0460ds;

public class InAppMessageSlideup extends InAppMessageBase {

    /* renamed from: i */
    private SlideFrom f2831i;

    /* renamed from: j */
    private int f2832j;

    public InAppMessageSlideup() {
        this.f2831i = SlideFrom.BOTTOM;
        this.f2797f = TextAlign.START;
    }

    public InAppMessageSlideup(JSONObject object, C0375bd appboyManager) {
        this(object, appboyManager, (SlideFrom) C0460ds.m528a(object, "slide_from", SlideFrom.class, SlideFrom.BOTTOM), object.optInt("close_btn_color"));
    }

    private InAppMessageSlideup(JSONObject object, C0375bd appboyManager, SlideFrom slideFrom, int chevronColor) {
        super(object, appboyManager);
        this.f2831i = SlideFrom.BOTTOM;
        this.f2831i = slideFrom;
        if (this.f2831i == null) {
            this.f2831i = SlideFrom.BOTTOM;
        }
        this.f2832j = chevronColor;
        this.f2796e = (CropType) C0460ds.m528a(object, "crop_type", CropType.class, CropType.FIT_CENTER);
        this.f2797f = (TextAlign) C0460ds.m528a(object, "text_align_message", TextAlign.class, TextAlign.START);
    }

    public SlideFrom getSlideFrom() {
        return this.f2831i;
    }

    public int getChevronColor() {
        return this.f2832j;
    }

    public JSONObject forJsonPut() {
        if (this.f2798g != null) {
            return this.f2798g;
        }
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.putOpt("slide_from", this.f2831i.toString());
            forJsonPut.put("close_btn_color", this.f2832j);
            forJsonPut.put("type", MessageType.SLIDEUP.name());
            return forJsonPut;
        } catch (JSONException e) {
            return null;
        }
    }
}
