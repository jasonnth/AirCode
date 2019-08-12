package com.appboy.models;

import com.appboy.enums.inappmessage.CropType;
import com.appboy.enums.inappmessage.MessageType;
import org.json.JSONException;
import org.json.JSONObject;
import p004bo.app.C0375bd;
import p004bo.app.C0460ds;

public class InAppMessageFull extends InAppMessageImmersiveBase {
    public InAppMessageFull() {
        this.f2796e = CropType.CENTER_CROP;
    }

    public InAppMessageFull(JSONObject object, C0375bd appboyManager) {
        super(object, appboyManager);
        this.f2796e = (CropType) C0460ds.m528a(object, "crop_type", CropType.class, CropType.CENTER_CROP);
    }

    public JSONObject forJsonPut() {
        if (this.f2798g != null) {
            return this.f2798g;
        }
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.put("type", MessageType.FULL.name());
            return forJsonPut;
        } catch (JSONException e) {
            return null;
        }
    }
}
