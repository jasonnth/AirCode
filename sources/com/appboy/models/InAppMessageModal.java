package com.appboy.models;

import com.appboy.enums.inappmessage.CropType;
import com.appboy.enums.inappmessage.ImageStyle;
import com.appboy.enums.inappmessage.MessageType;
import org.json.JSONException;
import org.json.JSONObject;
import p004bo.app.C0375bd;
import p004bo.app.C0460ds;

public class InAppMessageModal extends InAppMessageImmersiveBase {
    public InAppMessageModal() {
    }

    public InAppMessageModal(JSONObject object, C0375bd appboyManager) {
        super(object, appboyManager);
        if (this.f2822i.equals(ImageStyle.GRAPHIC)) {
            this.f2796e = (CropType) C0460ds.m528a(object, "crop_type", CropType.class, CropType.CENTER_CROP);
        } else {
            this.f2796e = (CropType) C0460ds.m528a(object, "crop_type", CropType.class, CropType.FIT_CENTER);
        }
    }

    public JSONObject forJsonPut() {
        if (this.f2798g != null) {
            return this.f2798g;
        }
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.put("type", MessageType.MODAL.name());
            return forJsonPut;
        } catch (JSONException e) {
            return null;
        }
    }
}
